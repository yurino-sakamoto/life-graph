import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    accountInfo: []
  },
  mutations: {
    setAccount (state, payload) {
      state.accountInfo = payload
    },
    resetAccountInfo (state) {
      state.accountInfo = ''
    }
  },
  actions: {
    async accountAction ({ commit }, userId) {
      const url = '/api/auth/accounts/' + userId
      await axios.get(url).then(res => commit('setAccount', res.data))
        .catch(err => err)
    }
  }
}
