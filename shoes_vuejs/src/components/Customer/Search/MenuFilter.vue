<template>
  <div class="group">
    <div @click.self="show1" class="sortchange">
      <div class="f1">
        <h4>Sắp xếp</h4>
        <p>
          {{
            $store.state.list.search.sle == 1
              ? "Giá Tăng dần"
              : $store.state.list.search.sle == 2
              ? "Giá Giảm dần"
              : ""
          }}
        </p>
      </div>
      <div
        v-if="filter1"
        :class="filter1 == true ? 'show1' : ''"
        class="menu_check_1"
      >
        <label
          @click="$store.state.list.search.sle = 1"
          class="item_menu_check"
        >
          <input type="radio" name="a" />
          <span class="test"></span>
          <div>Giá tăng dần</div>
        </label>
        <label
          @click="$store.state.list.search.sle = 2"
          class="item_menu_check"
        >
          <input type="radio" name="a" />
          <span class="test"></span>
          <div>Giá giảm dần</div>
        </label>
      </div>
    </div>
    <div class="tilte_filter">
      <h4>Bộ lộc</h4>
      <a href="" class="reset">Đặt lại</a>
    </div>
    <div @click.self="show2" id="sort2" class="sortchange">
      <div class="f1">
        <h4>Hãng hàng không</h4>
      </div>
      <div
        v-show="filter2"
        id="menu_check"
        :class="filter2 == true ? 'show1' : ''"
        class="menu_check_2"
      >
        <label
          v-for="item in trademark"
          :key="item.id"
          class="item_menu_check_2"
        >
          <input
            @click="check(item.id)"
            :id="'ip' + item.id"
            type="checkbox"
            name="b"
          />
          <span class="test2"></span>
          <div for="">{{ item.name }}</div>
        </label>
      </div>
    </div>
    <div @click.self="show2" id="sort2" class="sortchange">
      <div class="f1">
        <h4>Giá / Khách</h4>
      </div>
      <div
        v-show="filter2"
        id="menu_check"
        :class="filter2 == true ? 'show1' : ''"
        class="menu_check_2"
      >
        <div class="set_price_filter">
          <input
            v-model="$store.state.list.search.price"
            type="range"
            min="200000"
            max="10000000"
          />
          <span class="price"
            >Từ
            {{
              !$store.state.list.search.price
                ? `-`
                : $store.state.list.search.price
            }}
            VND</span
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { computed, ref } from "vue";
export default {
  setup() {
    store.dispatch("list/get_all_trademarks");
    const trademark = computed(() => {
      return store.state.list.trademark;
    });
    const check = (value) => {
      const data = document.getElementById("ip" + value);
      if (data.checked) {
        store.state.list.search.group_trademark.push(value);
      } else {
        store.state.list.search.group_trademark.pop(value);
      }
    };
    const filters = ref(false);
    const filter1 = ref(false);
    const filter2 = ref(false);
    const shoenext = () => {
      store.state.shownext = !store.state.shownext;
    };
    const show1 = () => {
      filter1.value = !filter1.value;
    };
    const show2 = () => {
      filter2.value = !filter2.value;
    };
    const showop = () => {
      filters.value = !filters.value;
    };
    return {
      filter1,
      filter2,
      show1,
      show2,
      filters,
      showop,
      shoenext,
      trademark,
      check,
    };
  },
};
</script>
<style scoped>
.reset {
  font-weight: 800;
  cursor: pointer;
  color: black;
  text-decoration: none;
}
.set_price_filter {
  position: relative;
}
.set_price_filter > span {
  font-size: 12px;
}
.set_price_filter > input {
  width: 100%;
}

.shows {
  opacity: 1 !important;
  right: 0 !important;
  pointer-events: visible !important;
}
.bgl {
  opacity: 1 !important;
  pointer-events: visible !important;
}
.bgx {
  transition: 2s;
  opacity: 0;
  background: rgba(0, 0, 0, 0.705);
  position: fixed;
  inset: 0;
  pointer-events: none;
}
.helko {
  transition: 0.3s;
}
.bg {
  background: rgba(0, 0, 0, 0.434);
  position: fixed;
  inset: 0;
  z-index: 100;
}
.item_seach {
  padding: 10px 20px;
  box-shadow: 0 0 2px black;
  display: flex;
  justify-content: space-between;
}
.group {
  position: sticky;
  top: 0;
  margin: 20px;
  background: rgb(216, 213, 213);
  padding: 10px;
  border-radius: 10px;
}
.list_card {
  padding: 30px;
}
.show1 {
  animation: example 0.2s ease-in forwards;
}
@keyframes example {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.showops {
  animation: showpo 0.3s ease-in-out forwards;
}

.showops_bg {
  animation: showpo_bg 0.3s ease-in-out forwards;
}

@keyframes showpo {
  from {
    opacity: 0;
    transform: translateY(-100px);
  }
  to {
    opacity: 1;
    transform: translateY(50vh);
  }
}
@keyframes showpo_bg {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.f1 {
  pointer-events: none;
  display: flex;
  gap: 10px;
  flex-direction: column;
  align-items: center;
  padding: 10px;
}
.menu_check {
  z-index: 10;
}
.tilte_filter {
  padding-top: 30px;
  padding: 20px;
  display: flex;
  justify-content: space-between;
}
.left {
  padding: 10px;
  position: sticky;
  top: 0;
}
.sortchange {
  padding: 10px 20px;
  background: rgb(255, 255, 255);
  box-shadow: 0 0 4px black;
  position: relative;
  transition: 0.2s !important;
  border-radius: 10px;
  margin: 10px;
}
.menu_check {
  transition: 0.2s;
  padding: 10px 20px;
  position: absolute;
  left: 0;
  right: 0;
  background: rgb(255, 251, 251);
  border: 1px solid gray;
  top: 100%;
  z-index: 99;
}
p,
h4 {
  margin: 0;
  padding: 0;
}
.mainlist {
  height: 100vh;
  position: relative;
  padding-top: 100px;
}
.item_menu_check,
.item_menu_check_2 {
  padding: 10px;
  display: block;
  display: flex;
  gap: 10px;
}
.item_menu_check:hover,
.item_menu_check_2:hover {
  background: rgb(109, 109, 208);
}
.test,
.test2 {
  display: block;
  width: 15px;
  height: 15px;
  background: white;
  border: 1px solid black;
  border-radius: 50%;
}
.item_menu_check > input:checked ~ .test,
.item_menu_check_2 > input:checked ~ .test2 {
  background: rgb(5, 11, 136) !important;
  border: 3px solid gray;
}
.item_menu_check > input,
.item_menu_check_2 > input {
  display: none;
}
</style>
