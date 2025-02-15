<script setup>
import { ProductService } from '@/service/ProductService';
import { onMounted, ref } from 'vue';

const products = ref(null);

function formatCurrency(value) {
    return value.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
}

onMounted(() => {
    ProductService.getProductsSmall().then((data) => (products.value = data));
});
</script>

<template>
    <div class="card">
        <div class="font-semibold text-xl mb-4">Recent Sales</div>
        <DataTable :paginator="true" :rows="5" :value="products" responsiveLayout="scroll">
            <Column header="Image" style="width: 15%">
                <template #body="slotProps">
                    <img :alt="slotProps.data.image" :src="`https://primefaces.org/cdn/primevue/images/product/${slotProps.data.image}`" class="shadow" width="50" />
                </template>
            </Column>
            <Column :sortable="true" field="name" header="Name" style="width: 35%"></Column>
            <Column :sortable="true" field="price" header="Price" style="width: 35%">
                <template #body="slotProps">
                    {{ formatCurrency(slotProps.data.price) }}
                </template>
            </Column>
            <Column header="View" style="width: 15%">
                <template #body>
                    <Button class="p-button-text" icon="pi pi-search" type="button"></Button>
                </template>
            </Column>
        </DataTable>
    </div>
</template>
