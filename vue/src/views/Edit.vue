<template>
  <div class="editorSection">
    <span class="editTitle">
      編集
    </span><br>
    <span class="editButton">
      <router-link to="/Top">
        Back
      </router-link>
    </span>
    <div class="editInfo">
      <p>グラフ情報入力</p>
      <div>
        年齢:<input v-model="ageAdd" type="text">歳
        <!-- 連続的なデータの書き換え用 -->
        <!-- <p>{{ ageAdd }}</p> -->
        <!-- 表示先指定 -->
      </div>
      <div>
        満足度:<input v-model="scoreAdd" type="text">％
      </div>
      <div>
        コメント:<input v-model="commentAdd" type="text">
      </div>
      <input v-model="Clear" type="reset">
      <input v-model="Add" type="submit">
    </div>
    <div class="editGraph">
      <Chart />
    </div>
  </div>
</template>

<script>
import Chart from '../components/Chart'

export default {
  components: {
    Chart
  },
  data () { // これでfunctionの役割
    // データの整理している
    return {
      ageAdd: 0, // '初期値はここに入れておく'
      scoreAdd: 0,
      commentAdd: 'Enter here'
    }
  }
}

</script>

<style>
.editorSection {
  background: linear-gradient(to bottom, rgba(133,199,183,0.95),#FFF);
  width: 100%;
  height: 1000px;
  margin: 0 auto;
  text-align: center;
  padding: 30px;
  display: inline-block;
}
.editorTitle {
  color: #565452;
  font-size: 30px;
}
.editButton {
  background: #FFF;
  color: #565452;
}
.editInfo {
  background:#FFF;
  border-radius: 25px;
  color: #565452;
  width: 40%;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 30px auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}
.cancelButton {
  padding: 6px;
}
.addButton{
  padding: 6px;
}
.editGraph {
  background:#FFF;
  color: #565452;
  border-radius: 25px;
  width: 50%;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}
</style>
