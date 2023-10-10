<template>
  <div
    @click="
      ($store.state.admin.update_airplane = null),
        this.$router.push({ name: 'airplane' })
    "
    :class="$store.state.admin.update_airplane == null ? null : 'show_bg'"
    class="black"
  ></div>
  <from
    :class="$store.state.admin.update_airplane == null ? null : 'show_bg'"
    class="parent"
  >
    <div class="top">
      <div class="airmar">
        <label for="">Hãng máy bay</label>
        <select>
          <option v-for="item in trademarks" :key="item.id" value="">
            {{ item.name + " " + this.$route.query.edit }}
          </option>
        </select>
      </div>
      <div class="name">
        <label for="">Mã Máy bay</label>
        <input type="text" name="" id="" />
      </div>
    </div>
    <div class="bottom">
      <div class="type_chair">
        <label for="">Tên Loại ghế</label>
        <select>
          <option v-for="item in typechair" :key="item.id" value="">
            {{ item.name }}
          </option>
        </select>
      </div>
      <div class="number">
        <label for="">Số Lượng</label>
        <input type="text" name="" id="" />
      </div>
      <div class="add_array">
        <button>Thêm</button>
      </div>
    </div>
    <div class="submit">
      <button>Cập Nhật</button>
    </div>
  </from>
</template>
<script>
import { computed } from "vue";
import store from "@/store";
export default {
  setup() {
    const trademarks = computed(() => {
      return store.state.trademarks;
    });
    const typechair = computed(() => {
      return store.state.type_chair;
    });
    return {
      trademarks,
      typechair,
    };
  },
};
</script>
<style scoped>
.add_array {
  display: flex;
  align-items: center;
  justify-content: end;
}
.top,
.bottom {
  display: flex;
  justify-content: center;
}
input,
select {
  padding: 10px;
  border: 1px solid black;
}
.top > div,
.bottom > div {
  display: flex;
  flex-direction: column;
  padding: 10px;
  gap: 10px;
}
.black {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.396);
  transition: 0.3s;
  opacity: 0;
  pointer-events: none;
}
.show_bg {
  opacity: 1 !important;
  pointer-events: visible !important;
}
.parent {
  transition: 0.3s;
  width: 500px;
  background: rgb(255, 255, 255);
  position: absolute;
  left: 50%;
  top: 50%;
  opacity: 0;
  transform: translate(-50%, -50%);
  border-radius: 10px;
}
.submit {
  text-align: center;
  padding: 10px;
}
button {
  background: blue;
  padding: 10px 30px;
  color: white;
  border: none;
  border-radius: 10px;
}
label {
  font-weight: 700;
}
</style>
