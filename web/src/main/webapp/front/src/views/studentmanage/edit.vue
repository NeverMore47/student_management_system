<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="学生编号">
        <el-input v-model="form.idcard"></el-input>
      </el-form-item>
      <el-form-item label="学生姓名">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
          <el-radio :label="0">未知</el-radio>
        </el-radio-group>
      </el-form-item>
       <el-form-item label="出生日期">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="出生日期" v-model="form.date1"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="籍贯">
        <el-input v-model="form.place"></el-input>
      </el-form-item>
      <el-form-item label="所在班级">
        <el-select v-model="form.department" placeholder="所在系">
          <el-option label="Zone one" value="shanghai"></el-option>
          <el-option label="Zone two" value="beijing"></el-option>
        </el-select>
        <el-select v-model="form.class" placeholder="所在班级">
          <el-option label="Zone one" value="shanghai"></el-option>
          <el-option label="Zone two" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="健康状况">
        <el-input v-model="form.health"></el-input>
      </el-form-item>
     <el-form-item label="毕业去向">
        <el-input type="textarea" v-model="form.direction"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        idcard: '',
        name: '',
        date1: '',
        place: '',
        sex: 1,
        department: 'shanghai',
        class: 'shanghai',
        health: '',
        direction: ''
      }
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$router.push('/studentmanage/index')
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

