<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div>
      <button class="ADD" type="submit">
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create New
      </button>
    </div>
    <div class="name">
      <label>Tên Loại Ghế</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.name == null ? null : { border: '1px solid red' }"
          v-model="typechair.name"
          type="text"
          name=""
        />
        <span v-if="erro.name" class="erro">{{ erro.name }}</span>
      </div>
    </div>
    <div class="name">
      <label> Giá trả thêm</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.price == null ? null : { border: '1px solid red' }"
          v-model="typechair.price"
          type="number"
          name=""
        />
        <span v-if="erro.price" class="erro">{{ erro.price }}</span>
      </div>
    </div>
    <div class="name">
      <label>Số kg hành lý</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.luggage == null ? null : { border: '1px solid red' }"
          v-model="typechair.luggage"
          type="number"
          name=""
        />
        <span v-if="erro.luggage" class="erro">{{ erro.luggage }}</span>
      </div>
    </div>
  </form>
</template>
<script>
import { reactive } from "vue";
import store from "@/store";

export default {
  setup() {
    const typechair = reactive({
      name: null,
      price: null,
      luggage: null,
    });
    const erro = reactive({
      name: null,
      luggage: null,
      price: null,
    });

    const validate = () => {
      typechair.name == null
        ? (erro.name = "không bỏ trống")
        : (erro.name = null);
      typechair.luggage == null
        ? (erro.luggage = "không bỏ trống")
        : (erro.luggage = null);
      typechair.price == null
        ? (erro.price = "không bỏ trống")
        : (erro.price = null);
    };

    const validate_lv2 = () => {
      typechair.luggage < 0
        ? (erro.luggage = "Không hợp lệ ")
        : (erro.luggage = null);
      typechair.price < 0
        ? (erro.price = "Không hợp lệ ")
        : (erro.price = null);
    };

    const onsubmit = () => {
      validate();
      if (erro.name != null || erro.lugguge != null || erro.price != null) {
        console.log("fail");
      } else {
        validate_lv2();
        if (erro.luggage != null || erro.price != null) {
          console.log("fail lv 2");
        } else {
          store.dispatch("md_typechair/post_typechair", typechair);
          store.state.md_typechair.mess_chaier = null;
          setTimeout(() => {
            store.state.md_typechair.mess_chaier = "Typechair Add success !";
          }, 300);
        }
      }
    };
    return {
      onsubmit,
      typechair,
      erro,
    };
  },
};
</script>
<style scoped>
/* //check */
.check {
  display: flex;
  flex-direction: column;
  padding: 20px;
  position: relative;
}
.erro {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  color: red;
  font-size: 13px;
}
.crud {
  display: flex;
  background: white;
  justify-content: space-between;
  padding: 10px;
}
.ADD {
  background: rgb(88, 202, 88);
  font-weight: 500;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 10px;
  font-weight: 600;
}
.crud > div {
  display: flex;
  align-items: center;
  gap: 10px;
}
input {
  padding: 10px 20px;
  border-radius: 5px;
  outline: none;
  border: 1px solid gray;
}
img {
  border-radius: 50%;
}
i {
  pointer-events: none;
}
label {
  font-weight: 700;
}
</style>
