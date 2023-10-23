<template>
  <div class="listHost">
    <h2 class="tile_hot">
      <font-awesome-icon :icon="['fas', 'earth-americas']" /> Những Địa Điểm nổi
      Bật
    </h2>
    <div v-if="data" class="list">
      <div
        @click="check(item.id)"
        v-for="item in data"
        :key="item.id"
        class="item"
      >
        <img
          class="img"
          :src="
            'http://localhost:8081/img/location/' + item.id + '&3&' + item.img
          "
        />
        <div class="content_item">
          <h4>{{ item.name }}</h4>
          <p>12.33 com</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { computed } from "vue";
import router from "@/router";
export default {
  setup() {
    const data = computed(() => {
      return store.getters["list/location"](5);
    });
    const check = (value) => {
      store.state.list.search.select = value;
      router.push({ name: "search", query: { to: value, round_trip: false } });
    };
    return {
      data,
      check,
    };
  },
};
</script>
<style scoped>
img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: 0;
  filter: brightness(60%);
}
.item {
  position: relative;
  height: 200px;
  border-radius: 20px;
  overflow: hidden;
}
.content_item {
  position: absolute;
  bottom: 20px;
  left: 20px;
  z-index: 10;
  color: white;
}
.list {
  padding: 40px;
  gap: 10px;
  display: grid;
}
.tile_hot {
  padding-left: 40px;
  padding-top: 50px;
}
</style>
