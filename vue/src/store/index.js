import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
// import chart from './modules/chart.js'

Vue.use(Vuex)

const config = {
  headers: {
    'Access-Control-Allow-Origin': '*'
  }
}

export default new Vuex.Store({
  state: {
    account: {
      // TODO: delete because of test
      login: 'ng'
    }
    // chart: {
    //   modules:
    //     chart: [],
    //     loaded: false
    // },
  },
  mutations: {
    // TODO: delete because of test
    setLogin (state, payload) {
      state.login = payload.login
    }
  },
  actions: {
    // TODO: delete because of test
    fetchLogin ({ commit }) {
      const url = '/api/auth/login'
      axios.get(url, config).then((res) => {
        commit('setLogin', res.data)
      })
    }
  }
})
