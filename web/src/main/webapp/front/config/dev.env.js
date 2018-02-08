'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://192.168.8.51:8080"'
})

// BASE_API: '"http://192.168.8.51:8080"'
// BASE_API: '"https://easy-mock.com/mock/5950a2419adc231f356a6636/vue-admin"'
