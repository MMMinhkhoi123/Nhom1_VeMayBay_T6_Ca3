<template>
  <div>
    <h3>Tiện Ích Thêm</h3>
    <basechonse></basechonse>
    <!-- <luggage></luggage> -->
    <payment></payment>
  </div>
</template>
<script>
import choseseat from "./ChoseaSeat.vue";
// import Luggage from "./LuggaGe.vue";
import Payment from "../../SumPay.vue";
export default {
  components: {
    basechonse: choseseat,
    // Luggage: Luggage,
    Payment: Payment,
  },
  setup() {
    return {};
  },
};
</script>
<style scoped>
h3 {
  margin-left: 10px;
}
div {
  padding: 10px;
}
</style>
