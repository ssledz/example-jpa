package design.infrastructure.jpa.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import design.domain.example5.audit.Auditable;
import design.domain.example5.audit.Auditor;

@Component
public class AuditListener {

	private static Auditor auditor;

	@Autowired
	public void setDateTimeProvider(Auditor auditor) {
		AuditListener.auditor = auditor;
	}

	@PrePersist
	@PreUpdate
	public void updateAudit(Object o) {
		if (o instanceof Auditable) {
			Auditable auditable = (Auditable) o;
			auditor.applyOn(auditable);
		}
	}
}