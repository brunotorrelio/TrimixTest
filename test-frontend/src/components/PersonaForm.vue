<template>
    <div class="container card mt-4 pt-4 pb-4">
      <div class="row">
        <div class="col-12">
            <h3 v-if="$route.params.id === 'alta'">Alta de Persona</h3>
            <h3 v-else>Edición de Persona</h3>
        </div>
      </div>  
      <form @submit="guardar" action="#" method="post">
        <div class="row">
          <div class="col-12">
          
            <div class="form-row">
              <div class="form-group col-md-4">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" v-model="persona.perNombre" placeholder="Nombre..." required>
              </div>
              <div class="form-group col-md-4">
                <label for="apellido">Apellido</label>
                <input type="text" class="form-control" v-model="persona.perApellido" placeholder="Apellido" required>
              </div>
              <div class="form-group col-md-4">
                <label for="numeroDocumento">Número de Documento</label>
                <input type="number" class="form-control" v-model="persona.perNumeroDocumento" placeholder="Número de documento..." required>
              </div>
            </div>

              <div class="form-row">
              <div class="form-group col-md-4">
                <label for="nombre">Tipo de Documento</label>
                  <select v-model="selected" class="form-control" id="listTipoDocumentoPersona">
                    <option v-for="option in options" v-bind:value="option">
                      {{ option }}
                    </option>
                  </select>
              </div>
              <div class="form-group col-md-4">
                <label for="apellido">Fecha de Nacimiento</label>
                <input type="text" class="form-control" v-model="persona.perFechaNacimientoString" placeholder="dd/mm/yyyy" required>
              </div>              
            </div> 
            
          </div>
        </div>

        <div class="row">
          <div class="col-12">
              <button type="button" class="btn btn-outline-danger" v-on:click="volver()" style="float: left"><i class="fa fa-arrow-left"></i> Atrás</button>

              <button type="submit" class="btn btn-outline-primary" style="float: right"><i class="fa fa-save"></i> Guardar</button>
          </div>
        </div> 
      </form>
    </div>
</template>

<script>

import axios from 'axios'
import { URL_GET_LIST_TIPOS_DOCUMENTO, URL_GET_PERSONA, URL_POST_SAVE_EDIT_PERSONA  } from '../config'
import { MESSAGE_NOT_CONNECTION } from '../messages'
import router from '../router/index'

import Swal from 'sweetalert2'

export default {
  name: 'PersonaForm',
  data: function () {
    return {
      persona: {
        perId: '',
        perNombre: '',
        perApellido: '',
        perNumeroDocumento: null,
        perTipoDocumento: '',
        perFechaNacimientoString: ''
      },
      options: [

      ],
      selected: ''
    }  
   },
  created() {

    var id = this.$route.params.id
    
    axios.get(URL_GET_LIST_TIPOS_DOCUMENTO)
      .then(response => {
          this.options = response.data.list

          if(id != 'alta'){

            axios.get(URL_GET_PERSONA + id)
              .then(response => {

                this.persona.perId = response.data.persona.perId
                this.persona.perNombre = response.data.persona.perNombre
                this.persona.perApellido = response.data.persona.perApellido
                this.persona.perNumeroDocumento = parseInt(response.data.persona.perNumeroDocumento)
                this.persona.perTipoDocumento = response.data.persona.perTipoDocumento
                this.persona.perFechaNacimientoString = response.data.persona.perFechaNacimientoString                                   
                this.selected = response.data.persona.perTipoDocumento
              })
          }
          else {
            this.selected = this.options[0];
          }
      })
  }, 
  methods: {
    volver() {
      router.push('/')
    },
    guardar(event) {
      event.preventDefault();

      var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{4}$/
       
      if(this.persona.perFechaNacimientoString.match(RegExPattern)){
          var id = this.$route.params.id

        if(id === 'alta'){
          this.persona.perId = null;
        }
        
        var e = document.getElementById("listTipoDocumentoPersona");
        var tipoDoc = e.options[e.selectedIndex].value;

        this.persona.perTipoDocumento = tipoDoc;

        var config = {
          headers: { 'Content-Type': 'application/json' }
        }  

        axios.post(URL_POST_SAVE_EDIT_PERSONA, this.persona, config)
          .then(response => {

            if(response.data.status == 'success'){
              var message = ''
              if(this.persona.perId == null){
                message = 'Alta realizada con éxito!'
              }
              else {
                message = 'Modificación realizada con éxito!'
              }

              Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: message,
                showConfirmButton: false,
                timer: 1500
              })
              router.push('/')
            }
            else {
              Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: response.data.message
              })
            }         
          })
          .catch(e => {
              console.log(e);
              Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: MESSAGE_NOT_CONNECTION
              })
          });
      }
      else {
        Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'El formato de la fecha es inválido'
              })
      } 
           
    },
  }
}
</script>
