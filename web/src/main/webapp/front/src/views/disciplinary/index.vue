<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="学生姓名" v-model="listQuery.studentRealName">
      </el-input>
      <el-input style="width: 200px;" class="filter-item" placeholder="学生编号" v-model="listQuery.studentNo">
      </el-input>
      <el-input style="width: 200px;" class="filter-item" placeholder="奖惩标题" v-model="listQuery.rePuName">
      </el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='学号' width="200">
        <template slot-scope="scope">
          {{scope.row.studentNo}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" width="200">
        <template slot-scope="scope"> 
          <span>{{ scope.row.studentRealName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖惩标题" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.rePuName}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleModifyStatus(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog title="修改" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="80px" style='width: 400px; margin-left:50px;'>
       <el-form-item label="学号">
          <el-input v-model="temp.studentNo" v-bind:disabled="dialogDicDisabled"></el-input>
        </el-form-item>
        <el-form-item label="学生名">
          <el-input v-model="temp.studentRealName" v-bind:disabled="dialogDicDisabled"></el-input>
        </el-form-item>
        <el-form-item label="奖惩标题" prop="rePuName">
          <el-input v-model="temp.rePuName" placeholder="添加奖惩标题"></el-input>
        </el-form-item>
        <el-form-item label="奖惩时间" prop="rePuDate">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="奖惩时间" v-model="temp.rePuDate"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="奖惩内容" prop="rePuDesc">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="添加奖惩记录" v-model="temp.rePuDesc">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateData">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findRewardsAndPunishmentList, updateReAndPu } from '@/api/student'

export default {
  data() {
    return {
      id: '',
      importance: '操作系统',
      list: null,
      total: 100,
      listLoading: true,
      dialogFormVisible: false,
      dialogStatus: '',
      listQuery: {
        page: 1,
        rows: 20,
        studentRealName: undefined,
        studentNo: undefined,
        rePuName: undefined
      },
      dialogDicDisabled: false,
      temp: {
        id: undefined,
        studentNo: undefined,
        studentRealName: undefined,
        rePuDesc: undefined,
        rePuDate: new Date(),
        rePuName: undefined
      },
       rules: {
        rePuDesc: [{ required: true, message: '奖惩记录不能为空', trigger: 'change' }],
        rePuName: [{ required: true, message: '奖惩标题不能为空', trigger: 'change' }]
      },
      downloadLoading: false
    }
  },
  filters: {
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      findRewardsAndPunishmentList(this.listQuery).then(response => {
        this.list = response.data.recordVOList
        this.total = response.data.count
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      if (this.listQuery.page === val) {
        return
      }
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
    handleFilter() {
      this.listQuery.page = 1
      this.fetchData()
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          var date = new Date(this.temp.rePuDate)
          var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
          const tempData = {
            id: this.temp.id,
            studentId: this.temp.studentId,
            rePuName: this.temp.rePuName,
            rePuDate: dateInfo,
            rePuDesc: this.temp.rePuDesc
          }
          updateReAndPu(tempData).then(response => {
            this.dialogFormVisible= false
            if(!response.success) {
              this.$notify({
                title: '失败',
                message: response.message,
                type: 'error',
                duration: 2000
              })
              return
            }

            this.$notify({
              title: '成功',
              message: '修改成功',
              type: 'success',
              duration: 2000
            })
            this.fetchData()
          })
        }
      })
    },
    handleModifyStatus(row) {  
      var date = new Date(row.rePuDate)
      var dateInfo = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()

      const tempData = {
        id: row.id,
        studentId: row.studentId,
        rePuName: row.rePuName,
        rePuDate: dateInfo,
        rePuDesc: row.rePuDesc,
        isDelete: 0
      }
      updateReAndPu(tempData).then(response => {
        if(!response.success) {
          this.$notify({
            title: '失败',
            message: response.message,
            type: 'error',
            duration: 2000
          })
          return
        }
        this.$notify({
          title: '成功',
          message: '修改成功',
          type: 'success',
          duration: 2000
        })
        this.fetchData()
      })

    }
  }
}
</script>