<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div>
      <button class="ADD" type="submit">
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create New
      </button>
    </div>
    <div class="code">
      <label>Tên Sân Bay</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.name == null ? null : { border: '1px solid red' }"
          v-model="AirField.name"
          type="text"
          name=""
        />
        <span v-if="erro.name" class="erro">{{ erro.name }}</span>
      </div>
    </div>
    <div>
      <label>Địa chỉ</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.adrress == null ? null : { border: '1px solid red' }"
          v-model="AirField.adrress"
          type="text"
          name=""
        />
        <span v-if="erro.adrress" class="erro">{{ erro.adrress }}</span>
      </div>
    </div>
    <div class="contry">
      <label>Tên quốc gia</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.contry == null ? null : { border: '1px solid red' }"
          v-model="AirField.contry"
          type="text"
          name=""
        />
        <span v-if="erro.contry" class="erro">{{ erro.contry }}</span>
      </div>
    </div>
  </form>
</template>
<script>
import { reactive } from "vue";
import store from "@/store";

export default {
  setup() {
    const AirField = reactive({
      name: null,
      adrress: null,
      contry: null,
    });
    const erro = reactive({
      name: null,
      adrress: null,
      contry: null,
    });
    const validate = () => {
      AirField.name == null
        ? (erro.name = "không bỏ trống")
        : (erro.name = null);
      AirField.adrress == null
        ? (erro.adrress = "không bỏ trống")
        : (erro.adrress = null);
      AirField.contry == null
        ? (erro.contry = "không bỏ trống")
        : (erro.contry = null);
    };
    const onsubmit = () => {
      validate();
      if (erro.name != null || erro.adrress != null || erro.contry != null) {
        console.log("fail");
      } else {
        store.dispatch("md_airfield/post_airfield", AirField);
        store.state.md_airfield.mess_airfield = null;
        setTimeout(() => {
          store.state.md_airfield.mess_airfield = "airfield Add success !";
        }, 200);
      }
    };
    return {
      onsubmit,
      location,
      AirField,
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
