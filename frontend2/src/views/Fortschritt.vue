<script>
export default {
    name: "Fortschritt"
}
</script>

<template>
    <div class="card">
        <div class="font-semibold text-xl mb-4">Fortschritt</div>
        <div class="flex flex-col md:flex-row gap-4">
            <div class="md:w-1/2">
                <ProgressBar :value="37"></ProgressBar>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>

</style>
