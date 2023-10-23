<template>
  <div id="my-chart">
    <table class="charts-css column show-heading show-labels data-spacing-10">
      <caption>
        Số lượng vé của hãng
      </caption>
      <thead>
        <tr>
          <th scope="col">Hãng bay</th>
          <th scope="col">Số lượng bán ra</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th>vietTel</th>
          <td style="--size: calc(20 / 100)">20</td>
        </tr>
        <tr>
          <th>efikfe</th>
          <td style="--size: calc(40 / 100)">40</td>
        </tr>
        <tr>
          <th>2018</th>
          <td style="--size: calc(60 / 100)">60</td>
        </tr>
        <tr>
          <th>2019</th>
          <td style="--size: calc(80 / 100)">80</td>
        </tr>
        <tr>
          <th>2020</th>
          <td style="--size: calc(100 / 100)">100</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
// import store from "@/store";
export default {
  setup() {},
};
</script>
<style scoped>
#my-chart {
  background: rgb(60, 59, 59);
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
}
#my-chart .column {
  --color: blue;
}
</style>
