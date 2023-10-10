<template>
  <div class="location">
    <div class="group_locaiton">
      <div class="filter">
        <div class="search">
          <input
            v-model="$store.state.md_customer.page.key"
            type="text"
            placeholder="Nhập Số điện thoại"
          /><font-awesome-icon
            @click="callkey"
            class="icon"
            :icon="['fas', 'magnifying-glass']"
          />
        </div>
      </div>
      <baselist></baselist>
    </div>
  </div>
</template>
<script>
import list from "@/components/Admin/LIST/List_Customer.vue";
import store from "@/store";
export default {
  components: {
    baselist: list,
  },
  setup() {
    const callkey = () => {
      store.dispatch("md_customer/get_customer");
    };
    return {
      callkey,
    };
  },
};
</script>
<style scoped>
.location {
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
  cursor: pointer;
}
/* list  */
.group_locaiton {
  margin-top: 10px;
  background: rgb(255, 255, 255);
}
</style>
