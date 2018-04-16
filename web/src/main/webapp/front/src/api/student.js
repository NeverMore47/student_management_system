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

export function saveRewardsAndPunishment(params) {
  return request({
    url: '/rewardAndPun/saveRewardsAndPunishment',
    method: 'get',
    params
  })
}

export function updateReAndPu(params) {
  return request({
    url: '/rewardAndPun/updateReAndPu',
    method: 'get',
    params
  })
}

export function avgResult(params) {
  return request({
    url: '/result/avgResult',
    method: 'get',
    params
  })
}

export function findStudentInfoDetail(params) {
  return request({
    url: '/studentInfo/findStudentInfoDetail',
    method: 'get',
    params
  })
}

export function findStudentExtendInfoList(params) {
  return request({
    url: '/studentExtendInfo/findStudentExtendInfoList',
    method: 'get',
    params
  })
}

export function updateStudentExtendInfo(params) {
  return request({
    url: '/studentExtendInfo/updateStudentExtendInfo',
    method: 'get',
    params
  })
}

export function findStudentAllInfoById(params) {
  return request({
    url: '/studentExtendInfo/findStudentAllInfoById',
    method: 'get',
    params
  })
}

