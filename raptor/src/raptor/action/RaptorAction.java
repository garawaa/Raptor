package raptor.action;

import java.util.Comparator;

import raptor.connector.Connector;
import raptor.swt.chat.ChatConsoleController;
import raptor.swt.chess.ChessBoardController;

/**
 * Currently all RaptoRactions must extend AbstractRaptorAction. All
 * RaptorActions must have a default no args constructor.
 */
public interface RaptorAction {

	public static Comparator<RaptorAction> NAME_COMPARATOR_ASCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return action1.getName().compareTo(action2.getName());
		}
	};

	public static Comparator<RaptorAction> NAME_COMPARATOR_DESCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return -1 * action1.getName().compareTo(action2.getName());
		}
	};

	public static Comparator<RaptorAction> CATEGORY_COMPARATOR_ASCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return action1.getCategory().toString().compareTo(
					action2.getCategory().toString());
		}
	};

	public static Comparator<RaptorAction> CATEGORY_COMPARATOR_DESCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return -1
					* action1.getCategory().toString().compareTo(
							action2.getCategory().toString());
		}
	};

	public static Comparator<RaptorAction> DESCRIPTION_COMPARATOR_ASCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return action1.getDescription().compareTo(action2.getDescription());
		}
	};

	public static Comparator<RaptorAction> DESCRIPTION_COMPARATOR_DESCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return -1
					* action1.getDescription().compareTo(
							action2.getDescription());
		}
	};

	public static Comparator<RaptorAction> KEYBINDING_COMPARATOR_ASCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return ActionUtils.keyBindingToString(action1).compareTo(
					ActionUtils.keyBindingToString(action2));
		}
	};

	public static Comparator<RaptorAction> KEYBINDING_COMPARATOR_DESCENDING = new Comparator<RaptorAction>() {
		public int compare(RaptorAction action1, RaptorAction action2) {
			return -1
					* ActionUtils.keyBindingToString(action1).compareTo(
							ActionUtils.keyBindingToString(action2));
		}
	};

	/**
	 * Just used so there is a nice way to group RaptorActions in tables. This
	 * gives the user something to sort on to easily find certain types of
	 * actions.
	 */
	public static enum Category {
		IcsCommands, Urls, PartnerTells, GameCommands, ConsoleCommands, Misc
	}

	public static class ContainerOrderComparator implements
			Comparator<RaptorAction> {
		protected RaptorActionContainer container;

		public ContainerOrderComparator(RaptorActionContainer container) {
			this.container = container;
		}

		public int compare(RaptorAction action1, RaptorAction action2) {
			return action1.getOrder(container) > action2.getOrder(container) ? 1
					: action1.getOrder(container) == action2
							.getOrder(container) ? 0 : -1;
		}
	}

	/**
	 * A RaptorAction container is a holder of actions. Usually this is a
	 * toolbar,menu, or a buttons panel of some type. RaptorActions can be
	 * ordered in a container.
	 */
	public static enum RaptorActionContainer {
		/**
		 * Used for the BugButtonWindowItem.
		 */
		BugButtons,
		/**
		 * Used for BughouseSuggest ChessBoardWindowItem tool bars.
		 */
		BughouseSuggestChessBoard,
		/**
		 * Used for InactiveController ChessBoardWindowItem tool bars.
		 */
		InactiveChessBoard,
		/**
		 * Used for PlayingController ChessBoardWindowItem tool bars.
		 */
		PlayingChessBoard,
		/**
		 * Used for ObserveController ChessBoardWindowItem tool bars.
		 */
		ObservingChessBoard,
		/**
		 * Used for SetupController ChessBoardWindowItem tool bars.
		 */
		SetupChessBoard,
		/**
		 * Used for ExamineController ChessBoardWindowItem tool bars.
		 */
		ExaminingChessBoard,
		/**
		 * Used for RegExController ChatConsoleWindowItem tool bars.
		 */
		RegExChatConsole,
		/**
		 * Used for ChannelController ChatConsoleWindowItem tool bars.
		 */
		ChannelChatConsole,
		/**
		 * Used for PersonController ChatConsoleWindowItem tool bars.
		 */
		PersonChatConsole,
		/**
		 * Used for PartnerController ChatConsoleWindowItem tool bars.
		 */
		BughousePartnerChatConsole,
		/**
		 * Used for MainController ChatConsoleWindowItem tool bars.
		 */
		MainChatConsole,
		/**
		 * Used for populating the actions in the FICS Menu.
		 */
		FicsMenu,
		/**
		 * Used for populating the actions in the BICS Menu.
		 */
		BicsMenu
	};

	/**
	 * Adds the specified RaptorActionToolbar to the list of containers this
	 * RaptorAction is displayed in.
	 */
	public void addContainer(RaptorActionContainer container, int order);

	/**
	 * Returns the category.
	 */
	public Category getCategory();

	/**
	 * Returns the ChatConsoleControllerSource for the specified action. May
	 * return null.
	 */
	public ChatConsoleController getChatConsoleControllerSource();

	/**
	 * Returns the ChessBoardControllerSource for the specified action. May
	 * return null.
	 */
	public ChessBoardController getChessBoardControllerSource();

	public Connector getConnectorSource();

	/**
	 * Returns an array of all of the RaptorActionToolbars this RaptorAction is
	 * showing in.
	 */
	public RaptorActionContainer[] getContainers();

	/**
	 * A detailed description of the action.
	 */
	public String getDescription();

	/**
	 * Returns the name of the icon in the resources/icons directory to use for
	 * this action.
	 * 
	 * This is the icon that will be displayed in toolbars and menus.
	 */
	public String getIcon();

	/**
	 * Returns the key code, 0 if it is not set.
	 * 
	 * @return
	 */
	public int getKeyCode();

	/**
	 * Returns the modifier key, 0 if it is not set.
	 */
	public int getModifierKey();

	/**
	 * Returns the unique short name of the action.
	 * 
	 * This is the short name of the action that will be displayed in
	 * preferences and the container.
	 */
	public String getName();

	/**
	 * Returns the order of the RaptorAction in the specified container.
	 */
	public int getOrder(RaptorActionContainer container);

	/**
	 * Returns true if the specified RaptorAction is showing in the specified
	 * container.
	 */
	public boolean isIn(RaptorActionContainer container);

	/**
	 * Returns true if this action is a system action. System actions can never
	 * be deleted. They always reside in the resources/action directory.
	 */
	public boolean isSystemAction();

	/**
	 * Removes a container from the list of containers this raptor action is
	 * being displayed in.
	 */
	public void removeContainer(RaptorActionContainer container);

	/**
	 * Executes the action.
	 */
	public void run();

	/**
	 * Sets the category.
	 */
	public void setCategory(Category category);

	/**
	 * Sets the ChatConsoleControllerSource this action was generated in.
	 */
	public void setChatConsoleControllerSource(
			ChatConsoleController chatConsoleController);

	/**
	 * Sets the ChessBoardControllerSource this action was generated in.
	 */
	public void setChessBoardControllerSource(
			ChessBoardController chessBoardController);

	public void setConnectorSource(Connector connector);

	/**
	 * Sets the order this RaptorAction is being displayed in in the specified
	 * container.
	 */
	public void setContainerOrder(RaptorActionContainer container, int order);

	/**
	 * Sets whether or not this action is a system action. This should really
	 * only be used by the ActionService class.
	 */
	public void setSystemAction(boolean systemAction);
}