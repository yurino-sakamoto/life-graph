import axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: '',
    token: '',
    error: '',
    success: true
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
    },
    error (state, err) {
      state.error = err
    },
    clearError (state) {
      state.error = ''
    },
    accountCreate (state) {
      state.success = !state.success
    }
  },
  actions: {
    create ({ commit }, data) {
      const url = '/api/auth/login'
      axios.post(url, data).then(res => commit('create', res.data))
        .catch(err => commit('error', err))
    },
    signup ({ commit }, data) {
      const url = '/api/auth/signup'
      axios.post(url, data).then(res => commit('accountCreate', res.data))
    }
  }
}
