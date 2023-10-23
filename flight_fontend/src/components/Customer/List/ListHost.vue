<template>
  <div class="listHost">
    <h2 class="tile_hot">
      <font-awesome-icon :icon="['fas', 'location-arrow']" />Tái khám phá bản
      thân ở châu Á và những nơi khác
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
          <h1>{{ item.name }}</h1>
          <p>{{ item.contry }}</p>
        </div>
      </div>
    </div>
  </div>
  <div class="parent_content">
    <p class="content">
      -Airmaxpro là công ty siêu ứng dụng du lịch và tiện ích sống, cung cấp nền
      tảng đặt vé máy bay và phòng khách sạn hàng đầu Đông Nam Á. Kể từ khi ra
      mắt mắt mắt mắt mắt mắt mắt mắt mắt tại Indonesia vào năm 2012 đến nay,
      Airmaxpro đã phát đến năm năm quốc gia khác trong khu vực, và được hơn 40
      triệu tín Riêng với dịch vụ đặt vé máy bay trực tuyến, Airmaxpro đã là đối
      tác của hơn 100 hãng hàng không nội địa và quốc tế, phục vụ hơn 200.000
      đường bay trên toàn thế giới.
    </p>
  </div>
</template>
<script>
import { computed } from "vue";
import store from "@/store";
import router from "@/router";
export default {
  setup() {
    const data = computed(() => {
      return store.getters["list/location2"](3);
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
.content {
  padding: 0 50px;
  font-size: 16px;
  text-align: justify;
}
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
  border-radius: 10px;
  overflow: hidden;
}
.content_item {
  position: absolute;
  top: 20px;
  left: 20px;
  z-index: 10;
  color: white;
}
.list {
  padding: 40px;
  gap: 10px;
  display: grid;
  justify-content: center;
  grid-template-columns: 300px 300px 300px;
}
.tile_hot {
  padding-left: 40px;
  padding-top: 50px;
}
</style>
