import request from '@/utils/request'

export function findStudentInfoList(params) {
  return request({
    url: '/studentInfo/findStudentInfoList',
    method: 'get',
    params
  })
}
