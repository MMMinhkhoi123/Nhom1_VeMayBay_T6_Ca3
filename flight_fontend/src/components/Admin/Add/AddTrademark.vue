<template>
  <form @submit.prevent="onsubmit" class="crud">
    <div v-if="!$store.state.md_trademark.data.id" class="adds">
      <button class="ADD" type="submit">
        <font-awesome-icon :icon="['fas', 'square-plus']" />
        Create
      </button>
    </div>
    <div
      v-if="
        $store.state.md_trademark.data.status ||
        $store.state.md_trademark.data.id
      "
      class="UPDATE"
    >
      <button class="update">Update</button>
      <button type="button" @click="reset" class="reload">
        <font-awesome-icon :icon="['fas', 'repeat']" />
      </button>
    </div>
    <div class="name">
      <label for="">Tên hãng hàng không</label>
      <div class="check">
        <input
          placeholder="Nhập..."
          :style="
            $store.state.md_trademark.erro.name == null
              ? null
              : { border: '1px solid red' }
          "
          v-model="$store.state.md_trademark.data.name"
          type="text"
          name=""
        />
        <span v-if="$store.state.md_trademark.erro.name" class="erro">{{
          $store.state.md_trademark.erro.name
        }}</span>
      </div>
    </div>
    <div class="image">
      <img
        width="50"
        height="50"
        v-if="data.url != null || $store.state.md_trademark.data.status"
        :src="
          $store.state.md_trademark.data.status
            ? 'http://localhost:8081/img/trademark/' +
              $store.state.md_trademark.data.id +
              '&3&' +
              $store.state.md_trademark.data.img
            : data.url
        "
      />
      <div class="check">
        <label class="labal">Chọn hình ảnh</label>
        <div class="main">
          <input
            :style="
              $store.state.md_trademark.erro.img == null
                ? null
                : { border: '1px solid red' }
            "
            id="file"
            type="file"
            name="file"
            multiple
            @change="showonsubmit"
          />
          <span v-if="$store.state.md_trademark.erro.img" class="erro">{{
            $store.state.md_trademark.erro.img
          }}</span>
          <label class="requid">* jpg,png</label>
        </div>
      </div>
    </div>
  </form>
</template>
<script>
import { reactive } from "vue";
import store from "@/store";

export default {
  setup() {
    const data = reactive({
      url: null,
      file: null,
    });
    const validate = () => {
      store.state.md_trademark.data.name == null ||
      store.state.md_trademark.data.name == ""
        ? (store.state.md_trademark.erro.name = "không bỏ trống")
        : (store.state.md_trademark.erro.name = null);
      store.state.md_trademark.data.img == null
        ? (store.state.md_trademark.erro.img = "không bỏ trống")
        : (store.state.md_trademark.erro.img = null);
    };

    const onsubmit = () => {
      validate();
      data.file != null
        ? ((store.state.md_location.data.img = data.file.name),
          (store.state.md_trademark.erro.img = null))
        : null;
      store.state.md_trademark.erro.name != null ||
      store.state.md_trademark.erro.img != null
        ? console.log("fail")
        : data.file != null
        ? (store.dispatch("md_trademark/post_trademark"),
          (store.state.md_trademark.mess_trademark = null))
        : ((store.state.md_location.mess_trademark = null),
          store.dispatch("md_trademark/put_trademark"));
    };

    const reset = () => {
      store.state.md_trademark.data.status = false;
      store.state.md_trademark.data.id = 0;
      store.state.md_trademark.data.img = null;
    };

    const showonsubmit = (event) => {
      const file = event.target.files;
      if (
        file[0].type.substring(6, 9) != "jpe" &&
        file[0].type.substring(6, 9) != "png"
      ) {
        store.state.md_trademark.erro.img = "Không đúng định dạng";
      } else {
        store.state.md_trademark.data.status = false;
        data.file = file[0];
        store.state.md_trademark.data.img = data.file.name;
        store.state.md_trademark.file_img = data.file;
        store.state.md_trademark.erro.img = null;
        data.url = URL.createObjectURL(file[0]);
      }
    };
    return {
      onsubmit,
      data,
      showonsubmit,
      reset,
    };
  },
};
</script>
<style scoped>
.adds {
  flex-direction: row !important;
  gap: 10px;
  align-items: flex-end !important;
}
.UPDATE {
  flex-direction: row !important;
  gap: 10px;
  align-items: flex-end !important;
}
.UPDATE > button {
  padding: 10px 30px;
  border-radius: 4px;
  font-weight: 700;
}
.UPDATE > button:nth-child(1) {
  border: none;
  background: rgb(228, 199, 14);
}
.UPDATE > button:nth-child(2) {
  border: none;
  background: rgba(87, 86, 88, 0.509);
}
.main {
  position: relative;
}
.requid {
  left: 0;
  top: 100%;
  position: absolute;
}
.labal {
  margin-bottom: 10px;
}
.image {
  display: flex;
  align-items: flex-end !important;
  flex-direction: row !important;
  gap: 10px;
}
/* erro inpuut */
.check {
  display: flex;
  flex-direction: column;
  position: relative;
}
.erro {
  position: absolute;
  top: 100%;
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
  padding: 20px 20px 30px 20px;
}
.ADD {
  background: rgb(88, 202, 88);
  font-weight: 500;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 4px;
  font-weight: 600;
}
.crud > div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
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
