import request from '@/utils/request'

export function findStudentInfoList(params) {
  return request({
    url: '/studentInfo/findStudentInfoList',
    method: 'get',
    params
  })
}

export function saveStudentInfo(params) {
  return request({
    url: '/studentInfo/saveStudentInfo',
    method: 'get',
    params
  })
}

export function deleteStudentInfo(params) {
  return request({
    url: '/studentInfo/deleteStudentInfo',
    method: 'get',
    params
  })
}

export function findResultList(params) {
  return request({
    url: '/result/findResultList',
    method: 'get',
    params
  })
}

export function updateResult(params) {
  return request({
    url: '/result/updateResult',
    method: 'get',
    params
  })
}

export function findRewardsAndPunishmentList(params) {
  return request({
    url: '/rewardAndPun/findRewardsAndPunishmentList',
    method: 'get',
    params
  })
}
