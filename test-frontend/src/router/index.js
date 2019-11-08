import Vue from 'vue'
import VueRouter from 'vue-router'
import PersonasList from '../components/PersonasList'
import PersonaForm from '../components/PersonaForm'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'home',
        component: PersonasList
    },
    {
        path: '/persona/:id',
        name: 'persona',
        component: PersonaForm
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router