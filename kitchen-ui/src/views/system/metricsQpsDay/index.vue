<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="命名空间" prop="environment">
        <el-input
          v-model="queryParams.environment"
          placeholder="命名空间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应用名称" prop="appName">
        <el-input
          v-model="queryParams.appName"
          placeholder="请输入应用名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="埋点key" prop="keyValue">
        <el-input
          v-model="queryParams.keyValue"
          placeholder="请输入埋点key"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="请求量" name="qps">
        <el-table
          v-loading="loading"
          :data="dayList"
          border
          :span-method="spanMethod"
          @selection-change="handleSelectionChange"
        >
          <el-table-column label="Key1" align="center" prop="keyOne" />
          <el-table-column label="Key2" align="center" prop="keyTwo" />
          <el-table-column label="Key3" align="center" prop="keyThird" />
          <el-table-column label="今日次数" align="center" prop="v1" />
          <el-table-column label="昨日次数" align="center" prop="v2" />
          <el-table-column label="命名空间" align="center" prop="environment" />
        </el-table>
        <pagination
          v-show="total > 0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          :pageSizes="[100, 200, 300, 500]"
          @pagination="getList"
        />
      </el-tab-pane>
      <el-tab-pane label="平均响应时间" name="rt">
        <el-table
          v-loading="loading"
          :data="dayList"
          border
          :span-method="spanMethod"
          @selection-change="handleSelectionChange"
        >
          <el-table-column label="Key1" align="center" prop="keyOne" />
          <el-table-column label="Key2" align="center" prop="keyTwo" />
          <el-table-column label="Key3" align="center" prop="keyThird" />
          <el-table-column label="今日平均时间" align="center" prop="v1" />
          <el-table-column label="昨日平均时间" align="center" prop="v2" />
          <el-table-column label="今日最小值" align="center" prop="min" />
          <el-table-column label="今日最大值" align="center" prop="max" />
          <el-table-column label="命名空间" align="center" prop="environment" />
        </el-table>
        <pagination
          v-show="total > 0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          :pageSizes="[100, 200, 300, 500]"
          @pagination="getList"
        />
      </el-tab-pane>
      <el-tab-pane label="成功率" name="third">角色管理</el-tab-pane>
    </el-tabs>
  </div>
</template>
  
  <script>
import {
  listDay,
  getDay,
  delDay,
  addDay,
  updateDay,
} from "@/api/system/metricsQpsDay";

export default {
  name: "Day",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      activeName: "qps",
      // 总条数
      total: 0,
      // 应用埋点表格数据
      dayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        time: null,
        appName: null,
        keyValue: null,
        hostName: null,
        logType: "QPS",
        v1: null,
        environment: null,
        keyOne: null,
        keyTwo: null,
        keyThird: null,
      },
      spanArr1: [],
      spanArr2: [],
      position1: 0,
      position2: 0,
      key: "key1",
      key2: "key2",
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        time: [{ required: true, message: "时间戳不能为空", trigger: "blur" }],
        appName: [
          { required: true, message: "应用名称不能为空", trigger: "blur" },
        ],
        keyValue: [
          { required: true, message: "埋点key不能为空", trigger: "blur" },
        ],
        logType: [
          { required: true, message: "数据类型不能为空", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询应用埋点列表 */
    getList() {
      this.loading = true;
      listDay(this.queryParams).then((response) => {
        this.dayList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.rowspan();
      });
    },

    handleClick(tab, event) {
      console.log(tab.name, event);
      if (tab.name == "qps") {
        this.queryParams.logType = "QPS";
      } else {
        this.queryParams.logType = "RT";
      }
      this.getList();
    },

    rowspan() {
      this.spanArr1 = [];
      this.spanArr2 = [];
      this.position1 = 0;
      this.position2 = 0;
      this.dayList.forEach((item, index) => {
        if (index === 0) {
          this.spanArr1.push(1);
          this.spanArr2.push(1);
          this.position1 = 0;
          this.position2 = 0;
        } else {
          if (this.dayList[index].keyOne === this.dayList[index - 1].keyOne) {
            this.spanArr1[this.position1] += 1;
            this.spanArr1.push(0);
          } else {
            this.spanArr1.push(1);
            this.position1 = index;
          }
          if (
            this.dayList[index].keyOne === this.dayList[index - 1].keyOne &&
            this.dayList[index].keyTwo === this.dayList[index - 1].keyTwo
          ) {
            this.spanArr2[this.position2] += 1;
            this.spanArr2.push(0);
          } else {
            this.spanArr2.push(1);
            this.position2 = index;
          }
        }
      });
    },
    spanMethod({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        const _row = this.spanArr1[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row, //行
          colspan: _col, //列
        };
      } else if (columnIndex === 1) {
        const _row = this.spanArr2[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row, //行
          colspan: _col, //列
        };
      }
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        time: null,
        appName: null,
        keyValue: null,
        hostName: null,
        logType: null,
        v1: null,
        environment: null,
        keyOne: null,
        keyTwo: null,
        keyThird: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加应用埋点";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getDay(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改应用埋点";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateDay(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDay(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除应用埋点编号为"' + ids + '"的数据项？')
        .then(function () {
          return delDay(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/day/export",
        {
          ...this.queryParams,
        },
        `day_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
  