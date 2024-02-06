<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Archive Photo</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">


        </ul>
        <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" v-model="searchphoto" placeholder="Search" aria-label="Search">
        </form>
      </div>
    </div>
  </nav>
  <div class="gi-background min-vh-100">
    <div class="container">
      <h1 class="text-center fw-bold">Best photos in archive</h1>

      <div class="row">
        <div class="col-4" v-for="(photo, index) in photosFiltrate" :key="index">
          <div class="card mb-3">
            <img :src="photo.url" class="card-img-top" alt="Photo Image">
            <div class="card-body">
              <h5 class="card-title">{{ photo.title }}</h5>
              <p class="card-text">{{ photo.description }}</p>
              <div class="row">
                <div class="col-6" v-for="category in photo.categories" :key="category.id">{{ category.name }}</div>
              </div>
            </div>
          </div>
        </div>

        <div v-if="photosFiltrate.length === 0" class="col-12">
          <h1 class="text-center gi-nothing display-5 fw-bold">No photo with this title</h1>
        </div>

      </div>

    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watchEffect } from 'vue'
import axios from "axios";

const API_URL = "http://localhost:8080/api/photos"

const photos = ref([]);
const searchphoto = ref('');


function getAllphotos() {
  axios.get(API_URL)
    .then(res => {
      const data = res.data;
      photos.value = data.filter(photo => photo.visible === true);
    })
    .catch(err => console.log(err));
}

const photosFiltrate = computed(() => {
  if (!searchphoto.value) {
    return photos.value;
  }

  const searchphotoLower = searchphoto.value.toLowerCase();
  return photos.value.filter((photo) =>
    photo.title.toLowerCase().includes(searchphotoLower)
  );
})

onMounted(() => {
  getAllphotos();
})
</script>
<style lang="scss" scoped>
.gi-background {
  background: rgb(248, 249, 250);
  background: linear-gradient(180deg, rgba(248, 249, 250, 1) 33%, rgba(0, 212, 255, 1) 100%);
  padding-top: 100px;
}

.card {
  min-height: 420px;
  transform: scale(0.8);
  transition: all .5s;


  .card-img-top {
    max-height: 280px;
    object-fit: cover;
    object-position: top;

  }

  &:hover {
    transform: scale(1);
  }
}

.gi-nothing {
  text-shadow: 3px 3px 5px #00d3fc;
  color: white;

}

h1 {
  color: white;
  text-shadow: 3px 3px 5px #00d3fc;
}
</style>