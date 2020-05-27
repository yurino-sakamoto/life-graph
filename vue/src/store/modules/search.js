import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    searchItems: [],
    error: '',
    showResult: false
  },
  mutations: {
    searchAPI (state, payload) {
      state.searchItems = payload
      state.showResult = true
    },
    clereSearchItems (state) {
      state.searchItems = ''
    },
    error (state, err) {
      state.error = err
    },
    clearError (state) {
      state.error = ''
    },
    loadFalse (state) {
      state.showResult = false
    }
  },
  actions: {
    async searchAPI ({ commit, rootState }, data) {
      const url = '/api/life-graphs'
      await axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        },
        params: data
      }).then(res => commit('searchAPI', res.data))
    },
    deleteGraphData ({ commit, rootState }, parentId) {
      const url = '/api/life-graphs/' + parentId
      axios.delete(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      })
        .catch(err => commit('error', err))
    }
  }
}
