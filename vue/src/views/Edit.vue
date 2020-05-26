<template>
  <div class="editSection">
    <div>
      <Header />
    </div>
    <div id="editForm">
      <h1>Edit</h1>
      <div id="input">
        <table id="field">
          <tr>
            <th class="th1" scope="row">
              <label for="age">
                年齢(歳)
              </label>
            </th>
            <th>
              <td>
                <input
                  id="ageInput"
                  ref="editor"
                  v-model="age"
                  type="number"
                  autocomplete="off"
                  placeholder="必須"
                  maxlength="3"
                  @keyup.enter="changeContents"
                >
              </td>
            </th>
          </tr><br>
          <tr>
            <th class="th1" scope="row">
              <label for="score">
                満足度(点)
              </label>
            </th>
            <td>
              <input
                id="scoreInput"
                ref="editor"
                v-model="score"
                type="number"
                autocomplete="off"
                placeholder="必須"
                maxlength="3"
              >
            </td>
          </tr><br>
          <tr>
            <th class="th1" scope="row">
              <label for="comment">
                コメント
              </label>
            </th>
            <td>
              <textarea
                id="commentInput"
                ref="editor"
                v-model="comment"
                cols="30"
                rows="5"
                placeholder="内容を入力してください"
                maxlength="255"
              />
            </td>
          </tr>
        </table>
        <div class="warning">
          <br><p>※スコアは-100から100の範囲で指定してください</p>
          <p>※コメントは250文字以内で入力してください</p><br>
        </div>
        <button
          class="resetButton"
          @click="removetext()"
        >
          リセット
        </button>
        <button
          class="button"
          :disabled="!canAdd"
          :class="{'disabled': !canAdd}"
          @click="add"
        >
          {{ changeButtonText }}
        </button>
        <div v-if="!ageCheck" class="ageCheck">
          年齢は1〜100で入力してください
        </div>
        <div v-if="!scoreCheck" class="scoreCheck">
          満足度は-100〜100で入力してください
        </div>
        <div v-if="editError" class="editError">
          更新に失敗しました
        </div>
        <div class="listInfo">
          <table class="tableRecord">
            <thead>
              <tr>
                <th class="th2">
                  年齢(歳)
                </th>
                <th class="th2">
                  満足度(点)
                </th>
                <th class="th2">
                  コメント
                </th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(content,index) in contents"
                :key="index"
              >
                <td class="addRecord">
                  {{ content.age }}
                </td>
                <td class="addRecord">
                  {{ content.score }}
                </td>
                <td class="commentTable">
                  {{ content.comment }}
                </td>
                <button
                  class="deleteButton"
                  @click="excludeFromArrayById(content.id)"
                >
                  削除
                </button>
                <button
                  class="editButton"
                  @click="edit(content.id)"
                >
                  修正
                </button>
              </tr>
            </tbody>
          </table>
          <div class="marginConfig" />
          <button
            class="button, reload"
            @click="update()"
          >
            更新
          </button>
        </div>
      </div>
      <div class="editGraph">
        <div
          class="chart"
        >
          <Chart refs="chart" />
        </div>
      </div><br><br>
    </div>
  </div>
</template>

<script scoped>
import Header from '../components/Header.vue'
import Chart from '../components/Chart.vue'
export default {
  name: 'Edit',
  components: {
    Header,
    Chart
  },
  data () {
    return {
      age: '',
      score: '',
      comment: '',
      isActive: false,
      contents: [],
      load: true,
      editId: 0,
      tmpId: 0
    }
  },
  computed: {
    loaded () {
      return this.$store.state.chart.loaded
    },
    changeButtonText () {
      return this.editIndex === 0 ? '追加' : '完了'
    },
    canAdd () {
      return this.inputCheck && this.ageCheck && this.scoreCheck && !this.ageExistCheck
    },
    inputCheck () {
      return this.age !== '' && this.score !== ''
    },
    ageCheck () {
      return this.age === '' || (this.age >= 0 && this.age <= 100)
    },
    scoreCheck () {
      return this.score >= -100 && this.score <= 100
    },
    ageExistCheck () {
      if (this.editId !== 0) return false
      if (!this.contents.find(content => content.age === Number(this.age))) return false
      return true
    },
    editError () {
      return this.$store.state.chart.error
    }
  },
  created () {
    this.$store.commit('chart/clearError')
    this.$store.dispatch('chart/addContent', this.$store.state.auth.userId)
  },
  mounted () {
    this.setContents()
  },
  methods: {
    setContents () {
      this.contents = this.$store.state.chart.contents.slice()
    },
    removetext () {
      this.age = ''
      this.score = ''
      this.comment = ''
      this.editId = 0
    },
    add () {
      this.isActive = true
      if (this.editId !== 0) {
        this.excludeFromArrayById(this.editId)
      }
      const content = this.contents.find(content => content.age === Number(this.age))
      if (content) {
        this.excludeFromArrayById(content.id)
      }
      const id = --this.tmpId
      this.contents.push({
        id: id,
        age: Number(this.age),
        score: Number(this.score),
        comment: this.comment
      })
      this.removetext()
      this.sort()
    },
    edit (id) {
      this.editId = id
      const content = this.contents.find(content => content.id === id)
      this.age = content.age
      this.score = content.score
      this.comment = content.comment
      this.$refs.editor.focus()
    },
    update () {
      this.$store.dispatch(
        'chart/addData',
        this.contents
      )
      const currentParentId = this.$store.state.chart.parentId
      const currentUserId = this.$store.state.auth.userId
      const apiContents = {
        parentId: currentParentId,
        userId: currentUserId,
        children: this.$store.state.chart.contents
      }
      this.$store.dispatch('chart/editContent', apiContents)
      // const userId = this.$store.state.auth.userId
      // this.$store.dispatch('chart/addContent', userId)
      // this.$refs.chart.createChart()
    },
    excludeFromArrayById (id) {
      this.contents = this.contents.filter(content => content.id !== id)
    },
    sort () {
      this.contents.sort(function (a, b) {
        if (a.age < b.age) return -1
        if (a.age > b.age) return 1
        return 0
      })
    }
  }
}
</script>

<style>
.editSection {
  background: #f3f3f9;
  height: 900px;
  text-align: center;
  width: 100%;
}

.warning {
  bottom: 130px;
  font-size: 12px;
  left: 340px;
  line-height: 60%;
  position: relative;
  text-align: left;
}

#input {
  background: #fff;
  border-radius: 20px;
  color: #565452;
  filter: drop-shadow(10px 10px 10px rgba(0, 0, 0, 0.2));
  float: left;
  font-size: 12pt;
  margin: 0 2px 50px 50px;
  padding: 24px;
  text-align: center;
  width: 620px;
  word-break: break-all;
}

#ageInput {
  background: #fafafa;
  font-family: 'Open Sans', sans-serif;
  font-size: 0.5em;
  height: 40px;
  width: 70px;
  font: 15px/24px sans-serif;
  box-sizing: border-box;
  padding: 0.3em;
  transition: 0.3s;
  letter-spacing: 1px;
  color: #565452;
  border: 1px solid #CCCCCC;
  border-radius: 4px;
  margin: .4rem 0;
  position: fixed;
  top: 10px;
  left: 107px;
}

#ageInput[type="text"]:focus {
  box-shadow: 0 0 1px 0 #7448FF;
  outline: 0;
  }

#scoreInput {
  background: #fafafa;
  font-family: 'Open Sans', sans-serif;
  font-size: 0.5em;
  height: 40px;
  width: 70px;
  font: 15px/24px sans-serif;
  box-sizing: border-box;
  padding: 0.3em;
  transition: 0.3s;
  letter-spacing: 1px;
  color: #565452;
  border: 1px solid #CCCCCC;
  border-radius: 4px;
  margin: .4rem 0;
  position: fixed;
  left: 107px;
  top: 66px;
}

#scoreInput[type="text"]:focus {
  box-shadow: 0 0 1px 0 #7448FF;
  outline: 0;
  }

#commentInput {
  background: #fafafa;
  font-family: 'Open Sans', sans-serif;
  font-size: 0.5em;
  height: 90px;
  width: 260px;
  resize: none;
  font: 15px/24px sans-serif;
  box-sizing: border-box;
  padding: 0.3em;
  transition: 0.3s;
  letter-spacing: 1px;
  color: #565452;
  border: 1px solid #CCCCCC;
  border-radius: 4px;
  margin: .4rem 0;
  position: fixed;
  left: 107px;
  top: 124px;
}

#commentInput[type="text"]:focus {
  box-shadow: 0 0 1px 0 #7448FF;
  outline: 0;
  }

.marginConfig {
  background: #fff;
  height: 90px;
}

.editGraph {
  background: #fff;
  border-radius: 20px;
  color: #565452;
  filter: drop-shadow(10px 10px 10px rgba(0, 0, 0, 0.2));
  float: right;
  height: 460px;
  font-size: 12pt;
  margin: 0 50px 0 -2px;
  padding: 0px;
  text-align: center;
  width: 640px;
  word-break: break-all;
}

.field {
  margin: 0 0 30px 0;
}

.tableRecord {
  margin: 20px 0 0 0;
}

.th1 {
  padding: 0 2px 8px 0;
  text-align: left;
  width: 80px;
}

.th2 {
  padding: 0 2px;
  text-align: left;
  width: 80px;
}

.listInfo {
  margin: 30px 0 0 0;
}

td {
  padding: 0 5px;
  text-align: left;
  width: 64px;
}

.addRecord {
  padding: 10px 0;
  text-align: center;
}

.commentTable {
  padding: 2px 0 2px 16px;
  text-align: left;
  width: 240px;
}

tr {
  height: auto;
}

h1 {
  color: #565452;
  font-size: 40px;
  margin: 20px 0 20px 0;
  padding-top: 80px;
  text-align: center;
}

.resetButton {
  background: rgb(204, 204, 204);
  border: none;
  outline: none;
  background:rgb(204,204,204);
  font-weight: bold;
  border-radius: 30px;
  color: #fff;
  cursor: pointer;
  font-size: 12pt;
  height: 40px;
  padding: 4px 8px;
  position: absolute;
  right: 136px;
  top: 220px;
  width: 100px;
}

.button {
  background: #fe5f52;
  border: none;
  font-weight: bold;
  border-radius: 30px;
  color: #fff;
  cursor: pointer;
  font-size: 12pt;
  height: 40px;
  outline: none;
  padding: 4px 8px;
  position: absolute;
  right: 30px;
  top: 220px;
  width: 100px;
}

.button.disabled {
  background: #ffbab3;
  cursor: pointer;
}

.deleteButton {
  background: rgb(204, 204, 204);
  border: none;
  font-weight: bold;
  border-radius: 30px;
  color: #fff;
  cursor: pointer;
  font-size: 12pt;
  outline: none;
  padding: 4px;
  position: absolute;
  right: 96px;
  width: 60px;
  margin: 0 auto;
}

.editButton {
  background: #fe5f52;
  border: none;
  font-weight: bold;
  border-radius: 30px;
  color: #fff;
  cursor: pointer;
  font-size: 12pt;
  outline: none;
  padding: 4px;
  position: absolute;
  right: 30px;
  width: 60px;

  /* :hover {
    background-color: #8566ce;
    color: #FFF;
    } */
}

/* 更新 */

button {
  background: #fe5f52;
  border: none;
  font-weight: bold;
  border-radius: 30px;
  color: #fff;
  cursor: pointer;
  font-size: 12pt;
  height: 40px;
  margin: 0 5px;
  outline: none;
  padding: 4px 8px;
  width: 90px;

  /* :hover {
    background-color: #8566ce;
    color: #FFF;
    } */
}

.reload {
  bottom: 30px;
  position: absolute;
  right: 30px;
}

.chart {
  padding: 8px 8px 8px 6px;
  width: 560px;
  height: auto;
  position: fixed;
  top: 26px;
  bottom: 30px;
  z-index: 90;
  left: 26px;
}

/* 入力フォーム */

/* バリデーション */

.ageCheck {
  font-size: 1em;
  left: 40px;
  margin: 2px;
  position: relative;
  text-align: center;
  width: 126px;
  line-height: 60%;
  bottom: 450px;
  font-weight: 400;
  color: #565452;
  width: 800px;
  letter-spacing: -1px;
}

.scoreCheck {
  left: 40px;
  margin: 2px;
  text-align: center;
  bottom: 130px;
  left: 340px;
  line-height: 60%;
  position: relative;
  left: 920px;
  bottom: 450px;
  font-size: 1em;
  font-weight: 400;
  color: #565452;
  width: 800px;
  text-align: center;
  letter-spacing: -1px;
}

.editError {
  background: #565452;
  color: #f6fb17;
  font-size: 1em;
  left: 40px;
  margin: 2px;
  position: relative;
  text-align: center;
  width: 160px;
}
</style>
