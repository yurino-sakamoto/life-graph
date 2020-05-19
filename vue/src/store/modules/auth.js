import axios from 'axios'

export default {
  namespaced: true,
  state: {
    tenant: '',
    userId: '',
    token: ''
  },
  mutations: {
    create (state, data) {
      state.tenant = ''
      state.token = data.token
      state.userId = data.id
    },
    destroy (state) {
      state.tenant = ''
      state.userId = ''
      state.token = ''
    },
    deleteToken (state) {
      state.token = ''
      state.userId = ''
    }
  },
  actions: {
    create ({ commit }, data) {
      const url = '/api/auth/login'
      axios.post(url, data).then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
