<template>
  <div class="ticker">
    <div class="group_ticker">
      <baselistww></baselistww>
    </div>
  </div>
</template>
<script>
import list from "@/components/Admin/LIST/List_Ticker.vue";
export default {
  components: {
    baselistww: list,
  },
  setup() {
    return {};
  },
};
</script>
<style scoped>
.ticker {
  margin: 10px;
}

/* filter */
.filter {
  padding: 10px;
  padding-bottom: 30px;
  display: flex;
}
.search {
  position: relative;
}
.search > input {
  padding: 15px 30px;
  border-radius: 5px;
}
.icon {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  font-size: 20px;
}
/* list  */
.group_ticker {
  margin-top: 10px;
  background: rgb(255, 255, 255);
}
</style>
