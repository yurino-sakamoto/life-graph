import Vue from 'vue'
import Vuex from 'vuex'
import chart from './modules/chart.js'
import account from './modules/account.js'
import search from './modules/search.js'
import createPersistedState from 'vuex-persistedstate'

import auth from '@/store/modules/auth'
import http from '@/store/modules/http'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    auth,
    http,
    chart,
    account,
    search
  },
  plugins: [createPersistedState({
    key: 'example',
    storage: window.sessionStorage
  })]
})
