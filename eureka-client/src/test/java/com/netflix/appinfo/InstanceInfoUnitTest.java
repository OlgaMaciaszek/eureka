package com.netflix.appinfo;

import com.netflix.discovery.util.InstanceInfoGenerator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Olga Maciaszek-Sharma
 */
public class InstanceInfoUnitTest {

	@Test
	public void testInstanceStatusUpdated() {
		InstanceInfo instanceInfo = InstanceInfoGenerator.takeOne();

		instanceInfo.setStatus(InstanceInfo.InstanceStatus.UP,
				InstanceInfo.InstanceStatus.OUT_OF_SERVICE);

		assertThat(instanceInfo.getStatus()).isEqualTo(InstanceInfo.InstanceStatus.OUT_OF_SERVICE);
	}

	@Test
	public void testInstanceStatusNotUpdatedWhenExpectedStatusNotMatched() {
		InstanceInfo instanceInfo = InstanceInfoGenerator.takeOne();

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() ->
						instanceInfo.setStatus(InstanceInfo.InstanceStatus.OUT_OF_SERVICE,
								InstanceInfo.InstanceStatus.DOWN));

	}

	@Test
	public void testInstanceStatusNotUpdatedWhenExpectedStatusNull() {
		InstanceInfo instanceInfo = InstanceInfoGenerator.takeOne();

		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
				instanceInfo.setStatus(null,
						InstanceInfo.InstanceStatus.OUT_OF_SERVICE)
		);

	}

}
