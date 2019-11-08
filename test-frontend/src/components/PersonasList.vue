<template>
  <div class="container mt-4">
    <div class="row">
      <div class="col-12">
        <button type="button" class="btn btn-outline-primary" v-on:click="InsertPersona()" style="float: right"><i class="fa fa-plus"></i> Nuevo</button>
      </div>
    </div>

    <div class="row justify-content-center card mt-3 pt-3">
      <div class="col-12">
         <h3>Filtros</h3>  
         <hr>
        <form @submit="buscarPersonas" action="#" method="get">

          <div class="form-row">
            <div class="form-group col-md-4">
              <label for="nombre">Nombre</label>
              <input v-model="nombre" type="text" class="form-control" id="nombre" placeholder="">
            </div>
            <div class="form-group col-md-4">
              <label for="nombre">Tipo Documento</label>
              <select v-model="selected" class="form-control" id="listTipoDocumento">
                <option v-for="option in options" v-bind:value="option">
                  {{ option }}
                </option>
              </select>
            </div>
            <div class="form-group col-md-4">
              <label for="btnBuscar">&nbsp</label>
              <button type="submit" class="btn btn-outline-primary mt-4" style="float:right"><i class='fa fa-search'></i> Buscar</button>
            </div>
          </div>
        </form>    
      </div>
    </div>  
    <div class="row justify-content-center card mt-3 pt-3">
      <div class="col-12">
        <h3>Personas</h3>
        
        <table class="table table-striped table-inverse">
          <thead class="thead-inverse">
            <tr>
              <th>Id</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Número Documento</th>
              <th>Tipo Documento</th>
              <th>Fecha Nacimiento</th>
              <th></th>
            </tr>
            </thead>
            <tbody>              
              <tr v-for="persona in personas" v-bind:key="persona.perId">
                <td scope="row">{{ persona.perId }}</td>
                <td>{{ persona.perNombre }}</td>
                <td>{{ persona.perApellido }}</td>
                <td>{{ persona.perNumeroDocumento }}</td>
                <td>{{ persona.perTipoDocumento }}</td>
                <td>{{ persona.perFechaNacimientoString }}</td>
                <td>
                  
                  <button type="button" class="btn btn-outline-primary btn-xs mr-1" v-on:click="editPersona(persona.perId)" ><i class="fa fa-edit"></i></button>
                   <button type="button" class="btn btn-outline-danger btn-xs ml-1" v-on:click="eliminarPersona(persona.perId)" ><i class="fa fa-trash"></i></button>
                </td>
              </tr>                 
            </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>

import axios from 'axios'
import { URL_GET_LIST_PERSONAS, URL_GET_LIST_TIPOS_DOCUMENTO, URL_DELETE_PERSONA } from '../config'
import { MESSAGE_NOT_CONNECTION } from '../messages'
import router from '../router/index'
import Swal from 'sweetalert2'

export default {
  name: 'PersonasList',
  data: function () {
    return {
      selected: '',
      personas: [
       
      ],
      options: [
        
      ],
      nombre: ''
      
    }
  },
  created () {

    var params = {
      tipoDoc: '',
      nombre: ''
    }

    axios.get(URL_GET_LIST_PERSONAS, {
      params: params
    })
    .then(response => {

      if(response.data.status == 'success'){
        this.personas = response.data.list
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
    })
    

    axios.get(URL_GET_LIST_TIPOS_DOCUMENTO)
      .then(response => {
        
        this.options = response.data.list
        this.options.unshift(' ')
      })
    
  },
  methods: {
    InsertPersona(){
      router.push('/persona/alta')
    },
    editPersona(id) {
      router.push('/persona/' + id)
     
    },
    eliminarPersona(id) {

      Swal.fire({
        title: '¿Está seguro de eliminar a esta persona?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.value) {

          axios.delete(URL_DELETE_PERSONA + id)
              .then(response => {
                 if(response.data.status == 'success'){

                   Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'La persona fue eliminada exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                  })
                    
                  var e = document.getElementById("listTipoDocumento");
                  var tipoDoc = e.options[e.selectedIndex].value;

                  var params = {
                    tipoDoc: tipoDoc,
                    nombre: nombre.value
                  }
                  axios.get(URL_GET_LIST_PERSONAS, {
                    params: params
                  })
                  .then(response => {
                    this.personas = response.data.list
                  })
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
              })        
        }
      })  
      
    },
    buscarPersonas(event) {
      
      var e = document.getElementById("listTipoDocumento");
      var tipoDoc = e.options[e.selectedIndex].value;

      var params = {
          tipoDoc: tipoDoc,
          nombre: nombre.value
        }

       axios.get(URL_GET_LIST_PERSONAS, {
          params: params
        })
        .then(response => {

          if(response.data.status == 'success'){
            this.personas = response.data.list
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
        })
      event.preventDefault();
    },
  },
  props: {
    msg: String
  }
}
</script>