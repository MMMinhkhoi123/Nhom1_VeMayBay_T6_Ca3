<template>
  <div class="TypeChair">
    <baseadd></baseadd>
    <div class="group_TypeChair">
      <baselist></baselist>
    </div>
  </div>
</template>
<script>
import add from "@/components/Admin/Add/AddTypeChair.vue";
import list from "@/components/Admin/LIST/List_Typechair.vue";
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
.TypeChair {
  margin: 10px;
}

/* list  */
.group_TypeChair {
  margin-top: 10px;
  background: rgb(255, 255, 255);
}
</style>
