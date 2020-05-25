import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)
// 手順（）
// ①ユーザーが入力した情報を箱に詰める()
// ②箱を引数としてsearchAPIに渡す
// ③searchAPIが動き、stateの中に情報が詰まる
// ④情報を取り出し、検索結果画面に詰める

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
      // console.log('actionにきたよー！')
      const url = '/api/life-graphs'
      await axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }, data).then(res => commit('searchAPI', res.data))
      // console.log('API終了')
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
