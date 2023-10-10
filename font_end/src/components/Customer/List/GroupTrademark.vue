<template>
  <div class="listtrademark">
    <h2 class="tile_hot">
      <font-awesome-icon :icon="['fas', 'city']" /> Các Hãng Máy bay phục vụ cho
      quý khách
    </h2>
    <div class="group_trademark">
      <div class="content">
        <h3>Đối tác hàng không</h3>
        <span>Đối tác hàng không nội địa và quốc tế</span>
        Những đối tác hàng không toàn cầu sẽ chắp cánh đưa bạn đến mọi địa điểm
        trên thế giới.
      </div>
      <div class="group_icon">
        <div class="item_trademark" v-for="item in data" :key="item.id">
          <baseimg v-if="item.img" :path="'trademark/' + item.img"></baseimg>
        </div>
      </div>
    </div>
  </div>
  <div class="resion">
    <h2>Tại sao nên đặt vé máy bay Tại Air Pro MAx</h2>
    <div class="club_resion">
      <div class="left">
        <strong>Kết quả tìm kiếm bao quát</strong>
        <p>
          Chỉ với một cú nhấp chuột, dễ dàng so sánh các chuyến bay của Vietnam
          Airlines, Jetstar, Vietjet và các chặng bay quốc tế khác với 100.000
          đường bay ở châu Á Thái Bình Dương và châu Âu.
        </p>
      </div>
      <div class="right">
        <strong>Phương thức thanh toán an toàn và linh hoạt</strong>
        <p>
          Giao dịch trực tuyến an toàn với nhiều lựa chọn như thanh toán tại cửa
          hàng tiện lợi, chuyển khoản ngân hàng, thẻ tín dụng đến Internet
          Banking. Không tính phí giao dịch.
        </p>
      </div>
    </div>
  </div>
</template>
<script>
import { computed } from "vue";
import store from "@/store";
import router from "@/router";
import img from "@/components/Admin/ImagesVue.vue";
export default {
  components: {
    baseimg: img,
  },
  setup() {
    store.dispatch("list/get_all_trademarks");
    const data = computed(() => {
      return store.getters["list/trademark"](6);
    });
    const check = (value) => {
      store.state.search.list_select = value;
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
.resion {
  text-align: center;
}
.club_resion {
  margin: 0 100px;
  display: grid;
  grid-template-columns: 1fr 1fr;
}
.club_resion > div {
  padding: 10px;
}
.listtrademark {
  margin: 30px;
  padding-bottom: 40px;
  text-align: center;
  border-bottom: 1px solid gray;
}
.group_trademark {
  margin: 0 100px;
  display: grid;
  grid-template-columns: 300px 1fr;
}
.content {
  text-align: justify;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.content > span {
  font-size: 12px;
  color: gray;
  margin-bottom: 10px;
}
h2 {
  padding: 30px;
}
img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.group_icon {
  display: grid;
  justify-content: center;
  gap: 10px;
  grid-template-columns: 100px 100px 100px;
}
</style>
