<template>
  <div v-if="$store.state.md_customer.customer" class="list_customer">
    <form v-for="item in data.list" :key="item.id" class="item">
      <div class="content">
        <span>{{ item.fullname }}</span>
        <span>{{ item.email }}</span>
        <span>{{ item.phone }}</span>
      </div>
      <div class="setting">
        <button
          @click="
            this.$router.push({
              name: 'customer-detail',
              params: {
                id: item.id,
              },
            })
          "
          type="button"
        >
          <font-awesome-icon :icon="['fas', 'circle-info']" /> detail
        </button>
      </div>
    </form>
    <!-- phân trang -->
    <div class="group_page">
      <div
        @click="chagepage($store.state.md_customer.page.page - 1)"
        v-if="data.page > 1"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </div>
      <div v-for="item in data.toltalpage" :key="item">
        <div
          @click="chagepage(item)"
          :style="
            item == data.page ? { background: 'blue', color: 'white' } : null
          "
          class="item_page"
        >
          {{ item }}
        </div>
      </div>
      <div
        @click="chagepage($store.state.md_customer.page.page + 1)"
        v-if="data.page < data.toltalpage"
        class="item_page"
      >
        <font-awesome-icon :icon="['fas', 'chevron-right']" />
      </div>
    </div>
  </div>
</template>
<script>
import store from "@/store";
import { computed } from "vue";
import { useRoute } from "vue-router";
export default {
  setup() {
    store.dispatch("md_customer/get_customer");
    const data = computed(() => {
      if (useRoute().params.ticker != undefined) {
        return store.getters["md_customer/getcustomer_idtick"](
          Number(useRoute().params.ticker)
        );
      }
      return store.state.md_customer.customer;
    });
    const chagepage = (item) => {
      store.state.md_customer.page.page = item;
      store.dispatch("md_customer/get_customer");
    };
    return {
      data,
      chagepage,
    };
  },
};
</script>
<style scoped>
/* pagnigation */

.group_page {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  position: absolute;
  right: 10px;
  bottom: 10px;
}
.item_page {
  display: flex;
  justify-content: center;
  align-items: center;
  background: white;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  border: 1px solid gray;
  cursor: pointer;
}
.list_customer {
  padding-bottom: 60px;
  position: relative;
}
.list_customer::-webkit-scrollbar {
  display: none;
}
.item {
  display: flex;
  grid-template-columns: 100px 1fr;
  background: rgb(255, 255, 255);
  position: relative;
  justify-content: space-between;
  align-content: center;
  margin: 0 100px;
  padding: 20px;
  margin-bottom: 10px;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.354);
}
.content {
  display: flex;
  gap: 20px;
  font-weight: 700;
}
.content > span {
  display: flex;
  justify-content: center;
  align-items: center;
}
button {
  padding: 10px 20px;
  border-radius: 10px;
  border: none;
  background: rgb(25, 30, 189);
  color: white;
  cursor: pointer;
}
</style>
