<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="时间戳" prop="time">
          <el-date-picker clearable
            v-model="queryParams.time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择时间戳">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="应用名称" prop="appName">
          <el-input
            v-model="queryParams.appName"
            placeholder="请输入应用名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="埋点key" prop="key">
          <el-input
            v-model="queryParams.key"
            placeholder="请输入埋点key"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="主机名称" prop="hostName">
          <el-input
            v-model="queryParams.hostName"
            placeholder="请输入主机名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="次数" prop="v1">
          <el-input
            v-model="queryParams.v1"
            placeholder="请输入次数"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="数值2" prop="v2">
          <el-input
            v-model="queryParams.v2"
            placeholder="请输入数值2"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="最小值" prop="min">
          <el-input
            v-model="queryParams.min"
            placeholder="请输入最小值"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="最大值" prop="max">
          <el-input
            v-model="queryParams.max"
            placeholder="请输入最大值"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:kv:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:kv:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:kv:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:kv:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="kvList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键" align="center" prop="id" />
        <el-table-column label="时间戳" align="center" prop="time" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="应用名称" align="center" prop="appName" />
        <el-table-column label="埋点key" align="center" prop="key" />
        <el-table-column label="主机名称" align="center" prop="hostName" />
        <el-table-column label="数据类型" align="center" prop="logType" />
        <el-table-column label="次数" align="center" prop="v1" />
        <el-table-column label="数值2" align="center" prop="v2" />
        <el-table-column label="最小值" align="center" prop="min" />
        <el-table-column label="最大值" align="center" prop="max" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:kv:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:kv:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改数据监控键值对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="时间戳" prop="time">
            <el-date-picker clearable
              v-model="form.time"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择时间戳">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="应用名称" prop="appName">
            <el-input v-model="form.appName" placeholder="请输入应用名称" />
          </el-form-item>
          <el-form-item label="埋点key" prop="key">
            <el-input v-model="form.key" placeholder="请输入埋点key" />
          </el-form-item>
          <el-form-item label="主机名称" prop="hostName">
            <el-input v-model="form.hostName" placeholder="请输入主机名称" />
          </el-form-item>
          <el-form-item label="次数" prop="v1">
            <el-input v-model="form.v1" placeholder="请输入次数" />
          </el-form-item>
          <el-form-item label="数值2" prop="v2">
            <el-input v-model="form.v2" placeholder="请输入数值2" />
          </el-form-item>
          <el-form-item label="最小值" prop="min">
            <el-input v-model="form.min" placeholder="请输入最小值" />
          </el-form-item>
          <el-form-item label="最大值" prop="max">
            <el-input v-model="form.max" placeholder="请输入最大值" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listKv, getKv, delKv, addKv, updateKv } from "@/api/system/kv";
  
  export default {
    name: "Kv",
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
        // 总条数
        total: 0,
        // 数据监控键值表格数据
        kvList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          time: null,
          appName: null,
          key: null,
          hostName: null,
          logType: null,
          v1: null,
          v2: null,
          min: null,
          max: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          time: [
            { required: true, message: "时间戳不能为空", trigger: "blur" }
          ],
          appName: [
            { required: true, message: "应用名称不能为空", trigger: "blur" }
          ],
          key: [
            { required: true, message: "埋点key不能为空", trigger: "blur" }
          ],
          logType: [
            { required: true, message: "数据类型不能为空", trigger: "change" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询数据监控键值列表 */
      getList() {
        this.loading = true;
        listKv(this.queryParams).then(response => {
          this.kvList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
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
          key: null,
          hostName: null,
          logType: null,
          v1: null,
          v2: null,
          min: null,
          max: null
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
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加数据监控键值";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getKv(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改数据监控键值";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateKv(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addKv(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除数据监控键值编号为"' + ids + '"的数据项？').then(function() {
          return delKv(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/kv/export', {
          ...this.queryParams
        }, `kv_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  