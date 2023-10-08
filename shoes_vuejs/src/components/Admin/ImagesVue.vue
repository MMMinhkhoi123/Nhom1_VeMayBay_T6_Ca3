<template>
  <img :src="url" alt="" />
</template>
<script>
import { getStorage, ref, getDownloadURL } from "firebase/storage";
import { computed } from "vue";
export default {
  props: {
    path: String,
  },
  data() {
    return {
      url: null,
    };
  },
  computed() {
    const storage = getStorage();
    getDownloadURL(ref(storage, this.path))
      .then((e) => {
        this.url = e;
      })
      .catch((err) => console.log(err));
  },
  mounted() {
    const storage = getStorage();
    getDownloadURL(ref(storage, this.path))
      .then((e) => {
        this.url = computed(() => {
          return e;
        });
      })
      .catch((err) => console.log(err));
  },
};
</script>
<style scoped></style>
