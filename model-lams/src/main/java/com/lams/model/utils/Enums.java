package com.lams.model.utils;

public class Enums {

	public enum Mode {
		ACTIVE(0, "Active"), INACTIVE(1, "InActive"), BOTH(2, "Both");

		private Integer id;
		private String value;

		private Mode(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static Mode getType(Integer x) {
			switch (x) {
			case 0:
				return ACTIVE;
			case 1:
				return INACTIVE;
			case 2:
				return BOTH;
			default:
				return null;
			}
		}

		public static Mode[] getAll() {
			return Mode.values();
		}
	}

	public enum UserType {
		ALL(-1, "All"), BORROWER(1, "Borrower"), LENDER(2, "Lender");

		private int id;
		private String value;

		private UserType(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static UserType getType(Integer x) {
			switch (x) {
			case -1:
				return ALL;
			case 1:
				return BORROWER;
			case 2:
				return LENDER;
			default:
				return null;
			}
		}

		public static UserType[] getAll() {
			return UserType.values();
		}

	}
	
	public enum NotificationType {
		EMAIL(1, "Email"), SMS(2, "SMS"), SYSTEM(3, "SYSTEM");

		private int id;
		private String value;

		private NotificationType(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static NotificationType getType(Integer x) {
			switch (x) {
			case 1:
				return EMAIL;
			case 2:
				return SMS;
			case 3:
				return SYSTEM;
			default:
				return null;
			}
		}

		public static NotificationType[] getAll() {
			return NotificationType.values();
		}
	}
}
