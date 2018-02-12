<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="用户名" v-model="listQuery.userName">
      </el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加用户</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='编号' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="账号">
        <template slot-scope="scope">
          {{scope.row.userName}}
        </template>
      </el-table-column>
      
      <el-table-column class-name="status-col" label="角色名" width="300" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.userRoleId | statusFilter">{{scope.row.userRoleId | roleFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="操作" width="300">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button size="mini" v-if="scope.row.isDelete === 1" type="danger" @click="handleModifyStatus(scope.row,'freeze')">冻结</el-button>
          <el-button size="mini" v-else type="default" @click="handleModifyStatus(scope.row,'unfreeze')">解冻</el-button>
        </template>
      </el-table-column>
    </el-table>


    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="temp.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userPwd">
          <el-input v-model="temp.userPwd"></el-input>
        </el-form-item>
        <el-form-item label="角色类型">
          <el-select v-model="temp.userRoleId" placeholder="角色类型" style="width: 49%;">
            <el-option label="管理员" value="管理员"></el-option>
            <el-option label="老师" value="老师"></el-option>
            <el-option label="助教" value="助教"></el-option>
            <el-option label="校医" value="校医"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">提交</el-button>
        <el-button v-else type="primary" @click="updateData">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getUserList, addUser, delUser, updateUser } from '@/api/usermanage'

export default {
  data() {
    return {
      list: null,
      total: 20,
      listLoading: true,
      listQuery: {
        page: 1,
        rows: 20,
        userName: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '添加'
      },
      temp: {
        id: undefined,
        userName: '',
        userPwd: '',
        userRoleId: '管理员'
      },
      rules: {
        userPwd: [{ required: true, message: '密码不能为空', trigger: 'change' }],
        userName: [{ required: true, message: '用户名不能为空', trigger: 'change' }]
      },
      downloadLoading: false
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        2: 'gray',
        3: 'danger',
        4: 'warn'
      }
      return statusMap[status]
    },
    roleFilter(role) {
      const roleMap = {
        1: '管理员',
        2: '老师',
        3: '学生',
        4: '校医',
        5: '助教'
      }
      return roleMap[role]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getUserList(this.listQuery).then(response => {
        this.list = response.data.userList
        this.total = response.data.count
        this.listLoading = false
      })
    },
    checkRole(role) {
      const roleMap = {
        '管理员': 1,
        '老师': 2,
        '学生': 3,
        '校医': 4,
        '助教': 5
      }
      return roleMap[role]
    },
    checkRole1(role) {
      const roleMap = {
        1: '管理员',
        2: '老师',
        3: '学生',
        4: '校医',
        5: '助教'
      }
      return roleMap[role]
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.fetchData()
    },
    handleSizeChange(val) {
      if (this.listQuery.rows === val) {
        return
      }
      this.listQuery.rows = val
      this.fetchData()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        userName: '',
        userPwd: '',
        userRoleId: '管理员'
      }
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchData()
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          const tempRoleId = tempData.userRoleId = this.checkRole(tempData.userRoleId)
          addUser(tempData).then(response => {
            this.dialogFormVisible = false
            if (!response.success) {
              this.$notify({
                title: '失败',
                message: response.message,
                type: 'error',
                duration: 2000
              })
              return
            }
            this.temp.userRoleId = tempRoleId
            this.list.unshift(this.temp)
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })

            this.fetchData()
          })
        }
      })
    },
    handleUpdate(row) {
      const tempRow = {
        id: row.id,
        isDelete: row.isDelete,
        userName: row.userName,
        userPwd: row.userPwd,
        userRoleId: row.userRoleId
      }
      this.temp = Object.assign({}, tempRow) // copy obj
      this.temp.userRoleId = this.checkRole1(this.temp.userRoleId)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          const tempRoleId = tempData.userRoleId = this.checkRole(tempData.userRoleId)
          updateUser(tempData).then(response => {
            this.dialogFormVisible = false
            if (!response.success) {
              this.$notify({
                title: '失败',
                message: response.message,
                type: 'error',
                duration: 2000
              })
              return
            }
            this.temp.userRoleId = tempRoleId
            for (const v of this.list) {
              if(v.id === this.temp.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.temp)
                break
              }
            }
            this.$notify({
              title: '成功',
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleModifyStatus(row, type) {
      // freeze
      var isDelete, text
      if(type === 'freeze') {
        isDelete = 0
        text = '冻结'
      } else if(type === 'unfreeze') {
        isDelete = 1
        text = '解冻'
      }
      const tempData = {
        id: row.id,
        isDelete: isDelete
      }
      delUser(tempData).then(response => {
        console.log(response)
        if(!response.success) {
          this.$notify({
            title: '失败',
            message: text + '失败',
            type: 'error',
            duration: 2000
          })
        }

        for(const v of this.list) {
          if(v.id === tempData.id) {
            const index = this.list.indexOf(v)
            // this.list.splice(index, 1, tempData)
            console.log(index)
            this.list[index].isDelete = isDelete
            break
          }
        }
        this.$notify({
          title: '成功',
          message: text + '成功',
          type: 'success',
          duration: 2000
        })


      })
    }
  }
}
</script>
