<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.classId" placeholder="选择班级">
        <el-option label="一班" value="1"></el-option>
        <el-option label="二班" value="2"></el-option>
        <el-option label="三班" value="3"></el-option>
        <el-option label="四班" value="4"></el-option>
        <el-option label="五班" value="5"></el-option>
      </el-select>
      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.courseId" placeholder="选择课程">
        <el-option label="操作系统" value="1"></el-option>
        <el-option label="编译原理" value="2"></el-option>
        <el-option label="数据结构与算法" value="3"></el-option>
        <el-option label="人工智能导论" value="4"></el-option>  
      </el-select>
      <el-input style="width: 200px;" class="filter-item" placeholder="学生编号" v-model="listQuery.studentNo">
      </el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <!-- <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加成绩</el-button> -->
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='学号' width="300">
        <template slot-scope="scope">
          {{scope.row.studentNo}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.studentRealName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="班级" width="200" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.classId | classFilter}}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程名" align="center" width="250">
        <template slot-scope="scope">
          <span>{{scope.row.courseId | courseFilter}}</span>
        </template>
      </el-table-column>
      <el-table-column label="分数" align="center" width="100">
        <template slot-scope="scope">
          <span>{{scope.row.resultScore}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.start"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="学号">
          <el-input v-bind:disabled="disabledForm" v-model="temp.studentNo" aria-disabled=""></el-input>
        </el-form-item>
        <el-form-item label="学生姓名">
          <el-input v-bind:disabled="disabledForm" v-model="temp.studentRealName" aria-disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="课程名">
          <el-select v-bind:disabled="disabledForm" v-model="temp.courseId" placeholder="课程">
            <el-option label="操作系统" value="操作系统"></el-option>
            <el-option label="编译原理" value="编译原理"></el-option>
            <el-option label="数据结构与算法" value="数据结构与算法"></el-option>
            <el-option label="人工智能导论" value="人工智能导论"></el-option>
          </el-select>      
        </el-form-item>
        <el-form-item label="分数" prop="resultScore">
          <el-input v-model="temp.resultScore"></el-input>
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
import { findResultList, updateResult } from '@/api/student'

export default {
  data() {
    return {
      disabledForm: true,
      list: null,
      total: 100,
      listLoading: true,
      dialogFormVisible: false,
      dialogStatus: '',
      listQuery: {
        start: 1,
        rows: 20,
        classId: '1',
        courseId: '',
        studentNo: '',
      },
      textMap: {
        update: '修改',
        create: '添加'
      },
      temp: {
        id: undefined,
        studentNo: '',
        studentRealName: '',
        courseId: '操作系统',
        resultScore: ''
      },
      rules: {
        resultScore: [{ required: true, pattern: /^\d*$/, message: '分数添加有误', trigger: 'change'}]
      },
      downloadLoading: false
    }
  },
  filters: {
    classFilter(classid) {
      const classMap = {
        1: '一班',
        2: '二班',
        3: '三班',
        4: '四班',
        5: '五班'
      }
      return classMap[classid]
    },
    courseFilter(courseid) {
      const courseMap = {
        1: '操作系统',
        2: '编译原理',
        3: '数据结构与算法',
        4: '人工智能导论'
      }
      return courseMap[courseid]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      findResultList(this.listQuery).then(response => {
        this.list = response.data.resultList
        this.total = response.data.count
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      if (this.listQuery.start === val) {
        return
      }
      this.listQuery.start = val
      this.getList()
    },
    handleSizeChange(val) {
      if (this.listQuery.rows === val) {
        return
      }
      this.listQuery.rows = val
      this.getList()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        department: '操作系统',
        grade: ''
      }
    },
    handleFilter() {
      this.listQuery.start = 1
      this.fetchData()
    },
    courseF(courseid, type) {
      var courseMap
      type = type ? type : 'default'
      if(type === 'default') {
        courseMap = {
          1: '操作系统',
          2: '编译原理',
          3: '数据结构与算法',
          4: '人工智能导论'
        }
      }else {
        courseMap = {
          '操作系统' : 1,
          '编译原理' : 2,
          '数据结构与算法' : 3,
          '人工智能导论' : 4
        }
      }
      return courseMap[courseid]
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.courseId = this.courseF(this.temp.courseId)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = {
            studentId: this.temp.studentId,
            courseId: this.courseF(this.temp.courseId, 'name'),
            resultScore: this.temp.resultScore
          }
          console.log(tempData)

          updateResult(tempData).then(response => {
            this.dialogFormVisible = false
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
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
            this.fetchData()
          })
        }
      })
    }
  }
}
</script>
