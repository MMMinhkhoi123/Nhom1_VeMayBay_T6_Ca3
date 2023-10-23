<template>
  <div class="flight">
    <baseadd></baseadd>
    <div class="group_flight">
      <div class="filter">
        <!-- null  -->
      </div>
      <baselist></baselist>
    </div>
  </div>
</template>
<script>
import add from "@/components/Admin/Add/AddFlight.vue";
import list from "@/components/Admin/LIST/List_Flight.vue";
export default {
  components: {
    baseadd: add,
    baselist: list,
  },
  setup() {
    return {};
  },
};
</script>
<style scoped>
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
.flight {
  margin: 10px 10px;
}
.group_flight {
  margin-top: 10px;
  background: rgb(255, 255, 255);
}
</style>
