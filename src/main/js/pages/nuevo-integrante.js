const React = require('react');
const {useState, useEffect} = require('react');
const { Link,useParams } = require('react-router-dom');
const client = require('../client');

const NuevoIntegrantePage = () => {

    let { id } = useParams();

    const [musicos, setMusicos] = useState([])
    const [instrumentos, setInstrumentos] = useState([])
    
    const [idMusico, setIdMusico] = useState('')
    const [idInstrumento, setIdInstrumento] = useState('')

    const handleSubmit = (evento)=>{
        evento.preventDefault();
        client({
            method: 'POST',
            path: '/api/integrantes',
            entity: {
                banda: 'http://localhost:8080/api/bandas/'+id,
                musico: 'http://localhost:8080/api/musicos/'+idMusico,
                instrumento: 'http://localhost:8080/api/instrumentos/'+idInstrumento},
            headers: {'Content-Type': 'application/json'}
        }).done(()=>{
           window.location = '/';
        })
    }

    useEffect(() => {
        client({
            method: 'GET',
            path: '/api/musicos'
        }).done(response=>{
            setMusicos(response.entity._embedded.musicos)
        })
        client({
            method: 'GET',
            path: '/api/instrumentos'
        }).done(response=>{
            setInstrumentos(response.entity._embedded.instrumentos)
        })

    },[])

    return (
        <>
            <h1>Nuevo Comprador</h1>
            <form onSubmit={handleSubmit}>

                <label htmlFor='musico'>Musico </label>
                <select name="musico" id="musico" onChange={(e)=>{setIdMusico(e.target.value)}}>
                    {musicos.map(musico => {	
                        const value = musico._links.self.href.split('/').slice(-1)
                        return (
                            <option key={value} value={value}>[{musico.nombre}]</option>
                        )
                    })}
                </select><br />
                
                <label>Instrumento </label>
                <select name="instrumento" id="instrumento" onChange={(e)=>{setIdInstrumento(e.target.value)}}>
                    {instrumentos.map(instrumento => {	
                        const value = instrumento._links.self.href.split('/').slice(-1)
                        return (
                            <option key={value} value={value}>({instrumento.nombre})</option>
                        )
                    })}
                </select><br />

                <input type="submit" value="Nuevo Integrante" />

            </form>
            <Link to="/">Volver</Link>
        </>
    )
}

module.exports = NuevoIntegrantePage;