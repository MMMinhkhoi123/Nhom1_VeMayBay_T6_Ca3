<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div>
      <button class="ADD" type="submit">
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create New
      </button>
    </div>
    <div class="name">
      <label for="">Tên hãng hàng không</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="erro.name == null ? null : { border: '1px solid red' }"
          v-model="trademark.name"
          type="text"
          name=""
        />
        <span v-if="erro.name" class="erro">{{ erro.name }}</span>
      </div>
    </div>
    <div class="image">
      <img width="50" height="50" v-if="data.url != null" :src="data.url" />
      <div class="check">
        <input
          :style="erro.img == null ? null : { border: '1px solid red' }"
          id="file"
          type="file"
          name="file"
          multiple
          @change="showonsubmit"
        />
        <span v-if="erro.img" class="erro">{{ erro.img }}</span>
      </div>
    </div>
  </form>
</template>
<script>
import { getStorage, ref, uploadBytes } from "firebase/storage";
import { reactive } from "vue";
import store from "@/store";

export default {
  setup() {
    const data = reactive({
      url: null,
      file: null,
    });

    const trademark = reactive({
      name: null,
      img: null,
    });

    const erro = reactive({
      name: null,
      img: null,
    });

    const validate = () => {
      trademark.name == null
        ? (erro.name = "không bỏ trống")
        : (erro.name = null);
      trademark.img == null ? (erro.img = "không bỏ trống") : (erro.img = null);
    };

    const storage = getStorage();
    const onsubmit = () => {
      validate();
      if (data.file != null) {
        location.img = data.file.name;
        erro.img = null;
      }
      if (erro.name != null || erro.img != null) {
        console.log("fail");
      } else {
        const storageRef = ref(storage, "trademark/" + data.file.name);
        uploadBytes(storageRef, data.file);
        trademark.img = data.file.name;
        store.dispatch("md_trademark/post_trademark", trademark);
        store.state.md_trademark.mess_trademark = null;
        setTimeout(() => {
          store.state.md_trademark.mess_trademark = "trademark Add success !";
        }, 1000);
      }
    };
    const showonsubmit = (event) => {
      const file = event.target.files;
      data.file = file[0];
      data.url = URL.createObjectURL(file[0]);
    };
    return {
      onsubmit,
      data,
      showonsubmit,
      trademark,
      erro,
    };
  },
};
</script>
<style scoped>
/* erro inpuut */
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
/* end erro input */
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
  font-weight: 600;
}
</style>
