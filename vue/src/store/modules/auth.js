import axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: '',
    token: ''
  },
  mutations: {
    create (state, data) {
      state.token = data.token
      state.userId = data.id
    },
    destroy (state) {
      state.userId = ''
      state.token = ''
    },
    deleteToken (state) {
      state.token = ''
      state.userId = ''
    }
  },
  actions: {
    // ログインボタンを押すと起動。userIdとtokenが更新される。
    create ({ commit }, data) {
      const url = '/api/auth/login'
      axios.post(url, data).then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
