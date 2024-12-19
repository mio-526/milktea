<template>
  <div>
    <el-upload
      class="upload-demo"
      :action="upload.url"
      list-type="picture-card"
      :headers="{token: token}"
      :file-list="files"
      :on-success="handleSuccess"
      :on-error="handleError"
      :on-remove="handleRemove"
      accept=".jpg,.jpeg,.png, .webp"
      :limit="limit"
      :class="{ imgHidden: files.length >= limit }"
    >
    <el-icon class="avatar-uploader-icon">
      <Plus />
    </el-icon>
    </el-upload>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';

const token = localStorage.getItem('token')

const props = defineProps({
  fileList: {
    type: Array,
    default: () => []
  },
  limit: {
    type: Number,
    default: 1
  }
});


const emit = defineEmits(['update:fileList', 'update:modelValue']);


const upload = ref({
  url: 'http://localhost:8888/api/upload',
  fileList: []
});


const files = computed({
  get() {
    return props.fileList;
  },
  set(val) {
    emit('update:fileList', val);
  }
});


const handleSuccess = (res, file, fileList) => {
  emit('update:fileList', fileList);
  emit('update:modelValue', res.data);
};

const handleError = () => {
  ElMessage.error('上传失败！');
};

const handleRemove = (file, fileList) => {
  emit('update:fileList', fileList);
};

</script>


<style scoped>
.upload-demo >>> input[type='file'] {
  display: none;
}

.imgHidden >>> .el-upload--picture-card {
  display: none;
}

</style>

