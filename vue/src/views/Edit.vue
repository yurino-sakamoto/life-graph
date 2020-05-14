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
      <form action="" method="post">
        <p>グラフ情報入力</p>
        <p>
          <label for="age">年齢:</label>
          <input type="text" name="age" required minlength="1" maxlength="2" size="4">
          <label for="age">歳</label>
        </p>
        <p>
          <label for="percentage">満足度:</label>
          <input type="text" name="percentage" required minlength="1" maxlength="3" size="4">
          <label for="age">%</label>
        </p>コメント<br>
        <textarea name="kanso" rows="4" cols="40" /><br>
        <input type="reset" name="reset" value="Clear">
        <input type="submit" name="send" value="Add">
      </form>
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
