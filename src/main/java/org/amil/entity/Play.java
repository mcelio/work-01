package org.amil.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Entidade Play representando cada jogada.
 * 
 * @author Marcos
 *
 */
@Entity
public class Play {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "GUN")
	private String gun;
	
	@Column(name = "KILLED")
	private String killed;
	
	@Column(name = "KILLER")
	private String killer;
	
	@Column(name="PLAY_DATE")
	private Date playDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MATCH_ID")
	private Match match;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGun() {
		return gun;
	}

	public void setGun(String gun) {
		this.gun = gun;
	}

	public String getKilled() {
		return killed;
	}

	public void setKilled(String killed) {
		this.killed = killed;
	}

	public String getKiller() {
		return killer;
	}

	public void setKiller(String killer) {
		this.killer = killer;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
}
