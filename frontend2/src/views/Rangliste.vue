<script setup>
import { CustomerService } from '@/service/CustomerService';
import { ProductService } from '@/service/ProductService';
import { onBeforeMount, reactive, ref } from 'vue';

const customers1 = ref(null);
const filters1 = ref(null);
const loading1 = ref(null);

const statuses = reactive(['unqualified', 'qualified', 'new', 'negotiation', 'renewal', 'proposal']);
const representatives = reactive([
    { name: 'Amy Elsner', image: 'amyelsner.png' },
    { name: 'Anna Fali', image: 'annafali.png' },
    { name: 'Asiya Javayant', image: 'asiyajavayant.png' },
    { name: 'Bernardo Dominic', image: 'bernardodominic.png' },
    { name: 'Elwin Sharvill', image: 'elwinsharvill.png' },
    { name: 'Ioni Bowcher', image: 'ionibowcher.png' },
    { name: 'Ivan Magalhaes', image: 'ivanmagalhaes.png' },
    { name: 'Onyama Limba', image: 'onyamalimba.png' },
    { name: 'Stephen Shaw', image: 'stephenshaw.png' },
    { name: 'XuXue Feng', image: 'xuxuefeng.png' }
]);


function getSeverity(status) {
    switch (status) {
        case 'unqualified':
            return 'danger';

        case 'qualified':
            return 'success';

        case 'new':
            return 'info';

        case 'negotiation':
            return 'warn';

        case 'renewal':
            return null;
    }
}

function formatDate(value) {
    return value.toLocaleDateString('de-DE', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    });
}
</script>

<template>
    <div class="card">
        <div class="font-semibold text-xl mb-4">Rangliste</div>
        <DataTable
            v-model:filters="filters1"
            :filters="filters1"
            :globalFilterFields="['Platz', 'Name', 'Punkte', 'Coins', 'status']"
            :loading="loading1"
            :paginator="true"
            :rowHover="true"
            :rows="10"
            :value="customers1"
            dataKey="id"
            filterDisplay="menu"
            showGridlines
        >
            <template #header>
            </template>
            <template #empty> Keine Teilnehmer gefunden </template>
            <template #loading> Lade Teilnehmerdaten...</template>
            <Column field="position" header="Platz" style="min-width: 4rem">
                <template #body="{ data }">
                    {{ data.position }}
                </template>
            </Column>
            <Column filterField="name" header="Name" style="min-width: 12rem">
                <template #body="{ data }">
                    <div class="flex items-center gap-2">
                        <img :class="`flag flag-${data.country.code}`" alt="flag" src="https://primefaces.org/cdn/primevue/images/flag/flag_placeholder.png" style="width: 24px" />
                        <span>{{ data.name }}</span>
                    </div>
                </template>
            </Column>
            <Column dataType="float" filterField="points" header="Punkte" style="min-width: 10rem">
                <template #body="{ data }">
                    {{ formatDate(data.points) }}
                </template>
            </Column>
            <Column dataType="float" filterField="coins" header="Coins" style="min-width: 10rem">
                <template #body="{ data }">
                    {{ formatDate(data.coins) }}
                </template>
            </Column>
            <Column bodyClass="text-center" dataType="boolean" field="status" header="Status" style="min-width: 8rem">
                <template #body="{ data }">
                    <i :class="{ 'pi-check-circle text-green-500 ': data.status, 'pi-times-circle text-red-500': !data.verified }" class="pi"></i>
                </template>
            </Column>
        </DataTable>
    </div>

</template>

<style lang="scss" scoped>
:deep(.p-datatable-frozen-tbody) {
    font-weight: bold;
}

:deep(.p-datatable-scrollable .p-frozen-column) {
    font-weight: bold;
}
</style>
