package com.API_Spring.demo.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cursos")
public class Cursos implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idcurso;
	
	private String nome;
	
	private String descricao;
	
	private int carga;
	
	private int totaulas;
	
	private int ano;

	public long getIdCurso() {
		return idcurso;
	}

	public void setId(long id) {
		this.idcurso = idcurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCarga() {
		return carga;
	}

	public void setCarga(int carga) {
		this.carga = carga;
	}

	public int getTotaulas() {
		return totaulas;
	}

	public void setTotaulas(int totaulas) {
		this.totaulas = totaulas;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
