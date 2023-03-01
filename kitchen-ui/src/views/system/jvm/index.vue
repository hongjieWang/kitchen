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
      <el-form-item label="应用名称" prop="appName">
        <el-input
          v-model="queryParams.appName"
          placeholder="请输入应用名称"
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
      <el-form-item label="时间戳" prop="time">
        <el-date-picker
          clearable
          v-model="queryParams.time"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择时间戳"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="峰值活动线程计数" prop="ptc">
        <el-input
          v-model="queryParams.ptc"
          placeholder="请输入峰值活动线程计数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="JVM 线程数" prop="tc">
        <el-input
          v-model="queryParams.tc"
          placeholder="请输入JVM 线程数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前活动后台进程线程数" prop="dtc">
        <el-input
          v-model="queryParams.dtc"
          placeholder="请输入当前活动后台进程线程数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前加载到 Java 虚拟机中的类的数量" prop="lcc">
        <el-input
          v-model="queryParams.lcc"
          placeholder="请输入当前加载到 Java 虚拟机中的类的数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="Java 虚拟机开始执行到目前已经卸载的类的总数"
        prop="tlcc"
      >
        <el-input
          v-model="queryParams.tlcc"
          placeholder="请输入Java 虚拟机开始执行到目前已经卸载的类的总数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="Java 虚拟机开始执行到目前已经卸载的类的总数"
        prop="ucc"
      >
        <el-input
          v-model="queryParams.ucc"
          placeholder="请输入Java 虚拟机开始执行到目前已经卸载的类的总数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="java 虚拟机使用的非堆内存的当前内存使用量"
        prop="nhmu"
      >
        <el-input
          v-model="queryParams.nhmu"
          placeholder="请输入java 虚拟机使用的非堆内存的当前内存使用量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="java 虚拟机用于对象分配的堆的当前内存使用量"
        prop="hmu"
      >
        <el-input
          v-model="queryParams.hmu"
          placeholder="请输入java 虚拟机用于对象分配的堆的当前内存使用量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="java虚拟机非堆初始化大小" prop="inhmu">
        <el-input
          v-model="queryParams.inhmu"
          placeholder="请输入java虚拟机非堆初始化大小"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Java虚拟机堆内存初始化大小" prop="ihmu">
        <el-input
          v-model="queryParams.ihmu"
          placeholder="请输入Java虚拟机堆内存初始化大小"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="java 虚拟机可使用非堆内存" prop="cnhmu">
        <el-input
          v-model="queryParams.cnhmu"
          placeholder="请输入java 虚拟机可使用非堆内存"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="java 虚拟机可使用堆内存" prop="chmu">
        <el-input
          v-model="queryParams.chmu"
          placeholder="请输入java 虚拟机可使用堆内存"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Java 虚拟机 最大非堆内存" prop="mnhmu">
        <el-input
          v-model="queryParams.mnhmu"
          placeholder="请输入Java 虚拟机 最大非堆内存"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="java 虚拟机最大堆内存" prop="mhmu">
        <el-input
          v-model="queryParams.mhmu"
          placeholder="请输入java 虚拟机最大堆内存"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="fgc执行次数" prop="fgcc">
        <el-input
          v-model="queryParams.fgcc"
          placeholder="请输入fgc执行次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ygc执行次数" prop="ygcc">
        <el-input
          v-model="queryParams.ygcc"
          placeholder="请输入ygc执行次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="fgc持续时间" prop="fgcd">
        <el-input
          v-model="queryParams.fgcd"
          placeholder="请输入fgc持续时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ygc持续时间" prop="ygcd">
        <el-input
          v-model="queryParams.ygcd"
          placeholder="请输入ygc持续时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="fgc累积回收时间" prop="fgct">
        <el-input
          v-model="queryParams.fgct"
          placeholder="请输入fgc累积回收时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Ygc累积回收时间" prop="ygct">
        <el-input
          v-model="queryParams.ygct"
          placeholder="请输入Ygc累积回收时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="fgc开始时间" prop="fgcs">
        <el-input
          v-model="queryParams.fgcs"
          placeholder="请输入fgc开始时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ygc开始时间" prop="ygcs">
        <el-input
          v-model="queryParams.ygcs"
          placeholder="请输入ygc开始时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="fgc结束时间" prop="fgce">
        <el-input
          v-model="queryParams.fgce"
          placeholder="请输入fgc结束时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ygc结束时间" prop="ygce">
        <el-input
          v-model="queryParams.ygce"
          placeholder="请输入ygc结束时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="cpu执行时间" prop="cpu">
        <el-input
          v-model="queryParams.cpu"
          placeholder="请输入cpu执行时间"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:jvm:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:jvm:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:jvm:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:jvm:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="jvmList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="数据类型" align="center" prop="logType" />
      <el-table-column label="应用名称" align="center" prop="appName" />
      <el-table-column label="主机名称" align="center" prop="hostName" />
      <el-table-column label="时间戳" align="center" prop="time" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数据类型" align="center" prop="dataType" />
      <el-table-column label="峰值活动线程计数" align="center" prop="ptc" />
      <el-table-column label="JVM 线程数" align="center" prop="tc" />
      <el-table-column
        label="当前活动后台进程线程数"
        align="center"
        prop="dtc"
      />
      <el-table-column
        label="当前加载到 Java 虚拟机中的类的数量"
        align="center"
        prop="lcc"
      />
      <el-table-column
        label="Java 虚拟机开始执行到目前已经卸载的类的总数"
        align="center"
        prop="tlcc"
      />
      <el-table-column
        label="Java 虚拟机开始执行到目前已经卸载的类的总数"
        align="center"
        prop="ucc"
      />
      <el-table-column
        label="java 虚拟机使用的非堆内存的当前内存使用量"
        align="center"
        prop="nhmu"
      />
      <el-table-column
        label="java 虚拟机用于对象分配的堆的当前内存使用量"
        align="center"
        prop="hmu"
      />
      <el-table-column
        label="java虚拟机非堆初始化大小"
        align="center"
        prop="inhmu"
      />
      <el-table-column
        label="Java虚拟机堆内存初始化大小"
        align="center"
        prop="ihmu"
      />
      <el-table-column
        label="java 虚拟机可使用非堆内存"
        align="center"
        prop="cnhmu"
      />
      <el-table-column
        label="java 虚拟机可使用堆内存"
        align="center"
        prop="chmu"
      />
      <el-table-column
        label="Java 虚拟机 最大非堆内存"
        align="center"
        prop="mnhmu"
      />
      <el-table-column
        label="java 虚拟机最大堆内存"
        align="center"
        prop="mhmu"
      />
      <el-table-column label="fgc执行次数" align="center" prop="fgcc" />
      <el-table-column label="ygc执行次数" align="center" prop="ygcc" />
      <el-table-column label="fgc持续时间" align="center" prop="fgcd" />
      <el-table-column label="ygc持续时间" align="center" prop="ygcd" />
      <el-table-column label="fgc累积回收时间" align="center" prop="fgct" />
      <el-table-column label="Ygc累积回收时间" align="center" prop="ygct" />
      <el-table-column label="fgc开始时间" align="center" prop="fgcs" />
      <el-table-column label="ygc开始时间" align="center" prop="ygcs" />
      <el-table-column label="fgc结束时间" align="center" prop="fgce" />
      <el-table-column label="ygc结束时间" align="center" prop="ygce" />
      <el-table-column label="cpu执行时间" align="center" prop="cpu" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:jvm:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:jvm:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改数据监控JVM参数对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="应用名称" prop="appName">
          <el-input v-model="form.appName" placeholder="请输入应用名称" />
        </el-form-item>
        <el-form-item label="主机名称" prop="hostName">
          <el-input v-model="form.hostName" placeholder="请输入主机名称" />
        </el-form-item>
        <el-form-item label="时间戳" prop="time">
          <el-date-picker
            clearable
            v-model="form.time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择时间戳"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="峰值活动线程计数" prop="ptc">
          <el-input v-model="form.ptc" placeholder="请输入峰值活动线程计数" />
        </el-form-item>
        <el-form-item label="JVM 线程数" prop="tc">
          <el-input v-model="form.tc" placeholder="请输入JVM 线程数" />
        </el-form-item>
        <el-form-item label="当前活动后台进程线程数" prop="dtc">
          <el-input
            v-model="form.dtc"
            placeholder="请输入当前活动后台进程线程数"
          />
        </el-form-item>
        <el-form-item label="当前加载到 Java 虚拟机中的类的数量" prop="lcc">
          <el-input
            v-model="form.lcc"
            placeholder="请输入当前加载到 Java 虚拟机中的类的数量"
          />
        </el-form-item>
        <el-form-item
          label="Java 虚拟机开始执行到目前已经卸载的类的总数"
          prop="tlcc"
        >
          <el-input
            v-model="form.tlcc"
            placeholder="请输入Java 虚拟机开始执行到目前已经卸载的类的总数"
          />
        </el-form-item>
        <el-form-item
          label="Java 虚拟机开始执行到目前已经卸载的类的总数"
          prop="ucc"
        >
          <el-input
            v-model="form.ucc"
            placeholder="请输入Java 虚拟机开始执行到目前已经卸载的类的总数"
          />
        </el-form-item>
        <el-form-item
          label="java 虚拟机使用的非堆内存的当前内存使用量"
          prop="nhmu"
        >
          <el-input
            v-model="form.nhmu"
            placeholder="请输入java 虚拟机使用的非堆内存的当前内存使用量"
          />
        </el-form-item>
        <el-form-item
          label="java 虚拟机用于对象分配的堆的当前内存使用量"
          prop="hmu"
        >
          <el-input
            v-model="form.hmu"
            placeholder="请输入java 虚拟机用于对象分配的堆的当前内存使用量"
          />
        </el-form-item>
        <el-form-item label="java虚拟机非堆初始化大小" prop="inhmu">
          <el-input
            v-model="form.inhmu"
            placeholder="请输入java虚拟机非堆初始化大小"
          />
        </el-form-item>
        <el-form-item label="Java虚拟机堆内存初始化大小" prop="ihmu">
          <el-input
            v-model="form.ihmu"
            placeholder="请输入Java虚拟机堆内存初始化大小"
          />
        </el-form-item>
        <el-form-item label="java 虚拟机可使用非堆内存" prop="cnhmu">
          <el-input
            v-model="form.cnhmu"
            placeholder="请输入java 虚拟机可使用非堆内存"
          />
        </el-form-item>
        <el-form-item label="java 虚拟机可使用堆内存" prop="chmu">
          <el-input
            v-model="form.chmu"
            placeholder="请输入java 虚拟机可使用堆内存"
          />
        </el-form-item>
        <el-form-item label="Java 虚拟机 最大非堆内存" prop="mnhmu">
          <el-input
            v-model="form.mnhmu"
            placeholder="请输入Java 虚拟机 最大非堆内存"
          />
        </el-form-item>
        <el-form-item label="java 虚拟机最大堆内存" prop="mhmu">
          <el-input
            v-model="form.mhmu"
            placeholder="请输入java 虚拟机最大堆内存"
          />
        </el-form-item>
        <el-form-item label="fgc执行次数" prop="fgcc">
          <el-input v-model="form.fgcc" placeholder="请输入fgc执行次数" />
        </el-form-item>
        <el-form-item label="ygc执行次数" prop="ygcc">
          <el-input v-model="form.ygcc" placeholder="请输入ygc执行次数" />
        </el-form-item>
        <el-form-item label="fgc持续时间" prop="fgcd">
          <el-input v-model="form.fgcd" placeholder="请输入fgc持续时间" />
        </el-form-item>
        <el-form-item label="ygc持续时间" prop="ygcd">
          <el-input v-model="form.ygcd" placeholder="请输入ygc持续时间" />
        </el-form-item>
        <el-form-item label="fgc累积回收时间" prop="fgct">
          <el-input v-model="form.fgct" placeholder="请输入fgc累积回收时间" />
        </el-form-item>
        <el-form-item label="Ygc累积回收时间" prop="ygct">
          <el-input v-model="form.ygct" placeholder="请输入Ygc累积回收时间" />
        </el-form-item>
        <el-form-item label="fgc开始时间" prop="fgcs">
          <el-input v-model="form.fgcs" placeholder="请输入fgc开始时间" />
        </el-form-item>
        <el-form-item label="ygc开始时间" prop="ygcs">
          <el-input v-model="form.ygcs" placeholder="请输入ygc开始时间" />
        </el-form-item>
        <el-form-item label="fgc结束时间" prop="fgce">
          <el-input v-model="form.fgce" placeholder="请输入fgc结束时间" />
        </el-form-item>
        <el-form-item label="ygc结束时间" prop="ygce">
          <el-input v-model="form.ygce" placeholder="请输入ygc结束时间" />
        </el-form-item>
        <el-form-item label="cpu执行时间" prop="cpu">
          <el-input v-model="form.cpu" placeholder="请输入cpu执行时间" />
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
import { listJvm, getJvm, delJvm, addJvm, updateJvm } from "@/api/system/jvm";

export default {
  name: "Jvm",
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
      // 数据监控JVM参数表格数据
      jvmList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        logType: null,
        appName: null,
        hostName: null,
        time: null,
        dataType: null,
        ptc: null,
        tc: null,
        dtc: null,
        lcc: null,
        tlcc: null,
        ucc: null,
        nhmu: null,
        hmu: null,
        inhmu: null,
        ihmu: null,
        cnhmu: null,
        chmu: null,
        mnhmu: null,
        mhmu: null,
        fgcc: null,
        ygcc: null,
        fgcd: null,
        ygcd: null,
        fgct: null,
        ygct: null,
        fgcs: null,
        ygcs: null,
        fgce: null,
        ygce: null,
        cpu: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询数据监控JVM参数列表 */
    getList() {
      this.loading = true;
      listJvm(this.queryParams).then((response) => {
        this.jvmList = response.rows;
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
        logType: null,
        appName: null,
        hostName: null,
        time: null,
        dataType: null,
        ptc: null,
        tc: null,
        dtc: null,
        lcc: null,
        tlcc: null,
        ucc: null,
        nhmu: null,
        hmu: null,
        inhmu: null,
        ihmu: null,
        cnhmu: null,
        chmu: null,
        mnhmu: null,
        mhmu: null,
        fgcc: null,
        ygcc: null,
        fgcd: null,
        ygcd: null,
        fgct: null,
        ygct: null,
        fgcs: null,
        ygcs: null,
        fgce: null,
        ygce: null,
        cpu: null,
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
      this.title = "添加数据监控JVM参数";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getJvm(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改数据监控JVM参数";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateJvm(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addJvm(this.form).then((response) => {
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
        .confirm('是否确认删除数据监控JVM参数编号为"' + ids + '"的数据项？')
        .then(function () {
          return delJvm(ids);
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
        "system/jvm/export",
        {
          ...this.queryParams,
        },
        `jvm_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
  