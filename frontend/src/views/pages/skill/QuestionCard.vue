<script setup>

import {onMounted, ref} from "vue";
import api from "@/api/api";

const optionList = ref([]);


onMounted(async () => {
    try {
        optionList.value = await api.get("/quiz");
        console.log(optionList.value);
    } catch (error) {
        console.log(error);
    }
})

</script>

<template>
    <Card style="max-width: 60rem; overflow: hidden">
        <template #header>
            <img alt="user header" src="https://primefaces.org/cdn/primevue/images/usercard.png" />
        </template>
        <template #title>Beispielquiz</template>
        <template #subtitle>Kategorie: Schweinebraten</template>
        <template #content>
            <p class="m-0">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Inventore sed consequuntur error repudiandae numquam deserunt
                quisquam repellat libero asperiores earum nam nobis, culpa ratione
                uam perferendis esse, cupiditate neque
                quas?
            </p>
            <div class="toggle">
                <input id="sizeWeight" checked="checked" name="sizeBy" type="radio" value="weight" />
                <label for="sizeWeight" >It's pretty, pretty, pretty, pretty good.</label>
                <input id="sizeDimensions" name="sizeBy" type="radio" value="dimensions" />
                <label for="sizeDimensions">100% yes</label>
                <input id="sizeWeight2" checked="checked" name="sizeBy" type="radio" value="weight" />
                <label for="sizeWeight2">lorem ipsum</label>
                <input id="sizeDimensions2" name="sizeBy" type="radio" value="dimensions" />
                <label for="sizeDimensions2">noch ein label</label>
            </div>
        </template>
        <template #footer>
            <div class="flex gap-4 right-0">
                <Button label="Abbrechen" raised severity="danger" />
                <Button label="OK" raised severity="success" />
            </div>
        </template>
    </Card>
</template>

<style lang="scss" scoped>


$open-sans: 'Open Sans', 'Helvetica', sans-serif;

$darkNavy: #213140;
$teal1: #66B3FB;
$teal2: rgba(75, 157, 234, 0.5);
$charcoal: #555555;
$gold: #B6985A;

$activeShadow: 0 0 10px rgba($teal1, .5);

@mixin focusOutline {outline: dotted 1px #CCC; outline-offset: .45rem;}
@mixin hideInput {width: 0; height: 0; position: absolute; left: -9999px;}
@mixin breakpoint($point) {
    @if $point == 1100 {
        @media (max-width: 1100px) { @content ; }
    }
    @else if $point == 800 {
        @media (max-width: 800px) { @content ; }
    }
}

.toggle {
    counter-reset: toggle-counter; /* Startet Zähler bei 1 */
}

.toggle {
    margin: 1.5rem 0 1.5rem; box-sizing: border-box;
    font-size: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-items: stretch;
    gap: 1rem;
    input {@include hideInput;}
    input + label {
        margin: 0; padding: .75rem 1rem; box-sizing: border-box;
        position: relative; display: flex;
        justify-content: left;
        align-items: center;
        border: solid 1px #DDD;
        box-shadow: 0 0 0 rgba(255,255,255,0);
        transition:   border-color .15s ease-out,
        color .25s ease-out,
        background-color .15s ease-out,
        box-shadow .15s ease-out;
        border-radius: 6px;

        font-size: 1rem; line-height: 1.2; font-weight: 600; text-align: left;
        /* Textfluss ermöglichen */
        white-space: normal; /* Standardmäßig ist dies "nowrap", kann also geändert werden */
        word-wrap: break-word; /* Für ältere Browser */
        word-break: break-word; /* Lange Wörter brechen bei Bedarf */
        overflow-wrap: break-word;

        /* Setze eine max. Breite für Umbrüche */
        max-width: 50%; /* Anpassbar, z. B. 50% für 2 Spalten */
        width: calc(50% - 0.5rem); /* Breite pro flex-Element */
        max-height: 5rem;

        /* Flex-Wert: je zwei Elemente pro Zeile */
        flex: 0 0 calc(50% - 0.5rem);
    }
    input:hover + label {border-color: $darkNavy;}
    input:checked + label {
        background-color: $teal2;
        color: #FFF;
        box-shadow: $activeShadow;
        border-color: $teal2;
        z-index: 1;
    }
    input:focus + label {@include focusOutline;}

    label::before {
        content: counter(toggle-counter);
        counter-increment: toggle-counter; /* Zählt Labels hoch */
        font-weight: bold;
        margin-right: 0.5rem;
        padding: 0.4rem 0.8rem;
        color: #5988bf; /* Nummernfarbe */
        background-color: $teal2;
        border: solid 1px #DDD;
        border-radius: 5px;
    }

    /* Ersetzt die Nummer durch ein Icon, wenn ausgewählt */
    input:checked + label::after {
        content: '\2713'; /* Unicode für ein Häkchen-Icon */
        color: white;
        background-color: $teal2;
        padding: 0.1rem 0.3rem;
        border-radius: 20%; /* Rundes Icon */
        font-size: 1rem;
        display: inline-block;
        width: 1.5rem;
        height: 1.5rem;

        margin-right: 1rem;
        position: absolute; /* relative vom Container */
        right: 0; /* Ganz rechts */
        top: 50%; /* Zentriert zur Vertikalen */
        transform: translateY(-50%); /* Für exakte Zentrierung */
    }

    @include breakpoint(800) {
        input + label {
            padding: .75rem .25rem;
            flex: 0 0 50%;
            display: flex; justify-content: center; align-items: center;
        }
    }
}
</style>
