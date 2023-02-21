<template>
  <div class="user">
    <el-form size="mini" :inline="true" class="demo-form-inline">
      <el-form-item label="名称">
        <el-input v-model="name" placeholder="名称"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="phone" placeholder="手机号"></el-input>
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker value-format="yyyy-MM-dd" v-model="createDate" type="daterange" range-separator="至"
          start-placeholder="开始日期" end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="query()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="addUser()" size="mini" icon="el-icon-plus">新增</el-button>
    <el-table :data="list" style="width: 100%">
      <el-table-column prop="id" label="ID" width="180">
      </el-table-column>
      <el-table-column prop="trueName" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="phone" label="手机号">
      </el-table-column>
      <el-table-column prop="createUserName" label="创建人">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间">
      </el-table-column>
      <el-table-column prop="updateUserName" label="修改人">
      </el-table-column>
      <el-table-column prop="updateDate" label="修改时间">
      </el-table-column>
      <el-table-column label="操作列">
        <template slot-scope="scope" v-if="scope.row.id !== 1">
          <el-button @click="updateData(scope.row)" type="text" size="mini">修改</el-button>
          <el-button @click="deleteData(scope.row)" type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
      :page-sizes="[1, 2, 5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
    <!-- 新增对话框 -->
    <el-dialog title="新增用户" :visible.sync="dialogVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="addUserParam.code"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="addUserParam.trueName"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="addUserParam.phone"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="addUserParam.password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUserRequest()">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 新增对话框 -->
    <el-dialog title="修改用户" :visible.sync="updateDialogVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="updateUserParam.code" disabled></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="updateUserParam.trueName"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="updateUserParam.phone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUserRequest()">确 定</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>
import axios from 'axios'
export default {
  data: function () {
    return {
      name: "",
      phone: "",
      page: 1,
      pageSize: 10,
      total: 0,
      list: [],
      createDate: '',
      dialogVisible: false,
      addUserParam: {
        code: "",
        trueName: "",
        phone: "",
        password: ""
      },
      updateUserParam: {
        code: "",
        trueName: "",
        phone: ""
      },
      updateDialogVisible: false
    }
  },
  mounted() {
    this.query()
  },
  methods: {
    updateData(item) {
      console.log(item)
      this.updateUserParam = {
        code: item.code,
        trueName: item.trueName,
        phone: item.phone
      }
      this.updateDialogVisible = true
    },
    query() {
      console.log("click", this.name, this.phone, this.createDate)
      const request = {
        "trueName": this.name,
        "phone": this.phone,
        "createDateStart": this.createDate ? this.createDate[0] : "",
        "createDateEnd": this.createDate ? this.createDate[1] : "",
        "page": this.page,
        "pageSize": this.pageSize
      }
      axios.post("http://localhost:8081/user/getPage/", request).then(res => {
        console.log(res.data.data)
        this.list = res.data.data
        this.total = res.data.total
      })
    },
    handleCurrentChange(page) {
      this.page = page
      this.query()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.query()
    },
    addUser() {
      console.log("addUser")
      this.dialogVisible = true
    },
    addUserRequest() {
      console.log("addUserParam", this.addUserParam)
      axios.post("http://localhost:8081/user/", this.addUserParam).then(res => {
        if (res.data === 1) {
          this.dialogVisible = false
          this.query()
        }
      })
    },
    updateUserRequest() {
      console.log("updateUserParam", this.updateUserParam)
      axios.put("http://localhost:8081/user/" + this.updateUserParam.code, this.updateUserParam).then(res => {
        if (res.data === 1) {
          this.updateDialogVisible = false
          this.query()
        }
      })
    },
    deleteData(item) {
      axios.delete("http://localhost:8081/user/" + item.code).then(res => {
        if (res.data === 1) {
          this.query()
        }
      })
    }
  }
}
</script>

<style>
.user {}

.table {
  width: 100%;
}
</style>
