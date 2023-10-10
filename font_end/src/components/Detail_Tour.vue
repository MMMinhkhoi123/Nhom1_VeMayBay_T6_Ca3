<template>
  <div class="item_detail">
    <div class="fromto">
      <slot name="from"></slot> -
      <slot name="to"></slot>
    </div>
    <div class="cate_left">
      <div class="cate_item">
        <slot name="img_trademark"></slot>
        <slot name="trademark"></slot>
      </div>
      <div class="cate_item">
        <slot name="timego"></slot> - <slot name="time_fished"></slot>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  setup() {},
};
</script>
<style scoped>
.fromto {
  padding: 10px;
  display: flex;
  gap: 2px;
}
.cate_left {
  display: flex;
  padding: 20px;
  justify-content: space-between;
}
.cate_item {
  display: flex;
  align-items: center;
  gap: 3px;
}
.item_detail {
  margin: 10px;
  box-shadow: 0 0 3px black;
}
</style>
